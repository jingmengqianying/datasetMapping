<template>
  <div>
    <div>
      <el-form :model="formData" label-width="120px" class="demo-ruleForm" status-icon>
        <el-form-item label="表名" prop="tableName" :rules="formRules.tableName">
          <el-select v-model="formData.tableName" class="m-2" placeholder="请选择">
            <el-option v-for="item in tableInfo" :key="item.name" :label="item.label" :value="item.name"/>
          </el-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="queryData">查询</el-button>
      <el-button type="primary" @click="addData">增加表数据</el-button>
      <el-button type="primary" @click="addColumnItem" v-show="tableShow">增加列信息</el-button>
    </div>

    <el-dialog title="提示" width="50%" v-model="addTableShow">
      <el-form :model="mapForm" label-width="120px" :rules="mapRules">
        <el-form-item hidden prop="id"></el-form-item>
        <el-form-item label="表名" prop="tableName">
          <el-select v-model="mapForm.tableName" class="m-2" placeholder="请选择">
            <el-option v-for="item in allTableInfoInDb" :key="item.name" :label="item.label" :value="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="表名标签" prop="tableDesc">
          <el-input v-model="mapForm.tableDesc" placeholder="标签" style="width:200px"/>
        </el-form-item>
      </el-form>

      <el-button type="primary" @click="addTableInfo">确定</el-button>
    </el-dialog>

    <div v-show="tableShow">
      <el-dialog title="提示" v-model="dialogColumnVisible" width="50%">
        <el-form :model="mapColumnForm" label-width="120px" :rules="mapRules">
          <el-form-item prop="id" v-model="mapColumnForm.columnInfo.id" hidden></el-form-item>
          <el-form-item prop="type" v-model="mapColumnForm.columnInfo.type" hidden></el-form-item>
          <el-form-item label="表名" prop="tableName">
            <el-select v-model="formData.tableName" class="m-2" placeholder="请选择" disabled>
              <el-option v-for="item in tableInfo" :key="item.name" :label="item.label" :value="item.name"/>
            </el-select>
          </el-form-item>
          <el-select v-model="mapColumnForm.columnInfo.name" class="m-2" placeholder="请选择字段">
            <el-option v-for="item in columnNames" :key="item.name" :label="item.name" :value="item.name"/>
          </el-select>
          <el-input v-model="mapColumnForm.columnInfo.label" placeholder="字段名称" style="width:200px"/>
        </el-form>
        <el-button type="primary" @click="addColumnInfo">确定</el-button>
      </el-dialog>
      <el-table :data="tableData" stripe style="width: 100%" height="300" v-loading="loading">
        <el-table-column prop="id" label="id" hidden></el-table-column>
        <el-table-column prop="tableName" label="表名" width="150"></el-table-column>
        <el-table-column prop="name" label="列名" width="150"></el-table-column>
        <el-table-column prop="label" label="标签" width="150"></el-table-column>
        <el-table-column prop="type" label="类型" width="150"></el-table-column>
        <el-table-column label="操作" width="400">
          <template #default="scope">
            <el-button size="default" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
              <template #reference>
                <el-button size="default" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 10px 0">
        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 20]"
            :disabled="disabled"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange(pageSize)"
            @current-change="handleCurrentChange(currentPage)"
        />
      </div>
    </div>
  </div>

</template>
<script>

import {reactive} from "vue";
import request from "@/utils/request"

const formRules = reactive({
  tableName: [{required: true, message: '请输入表名', trigger: 'blur',}]
})
const mapRules = reactive({
  tableName: [{required: true, message: '请输入表名', trigger: 'blur',}],
  tableDesc: [{required: true, message: '请输入表名标签', trigger: 'blur',}],
  columnName: [{required: true, message: '请输入列名', trigger: 'blur',}],
  columnDesc: [{required: true, message: '请输入列名标签', trigger: 'blur',}]
})

export default {
  name: 'MapperPage',
  components: {},
  data() {
    return {
      formRules: formRules,
      formData: {
        tableName: [],
        tableDesc: ''
      },
      mapRules: mapRules,
      mapForm: {
        tableName: [],
        tableDesc: '',
      },
      mapColumnForm: {
        columnInfo: {"id":"", "tableName":"", "name": "", "type": "", "label": ""}
      },
      allTableInfoInDb: [],
      tableInfo: [],
      dialogColumnVisible: false,
      addTableShow:false,
      columnNames: [],
      tableShow: false,
      loading: true,
      tableData: [],
      columnList: [],
      currentPage: 1,
      pageSize: 5,
      disabled: false,
      total: 5,
    }
  },
  mounted() {
    this.mapperData('admin')
  },
  watch: {
  },
  methods: {
    mapperData(role) {
      request.get('/api/manager/getByRole', {params: {role: role}}).then(res => {
        if (res.code === '0') {
          res.data.forEach((rowData) => {
            this.tableInfo.push({"name": rowData.tableName, "label": rowData.tableDesc})
          })
        } else {
          alert("获取表信息错误！")
        }
      })
    },
    queryData() {
      request.get('/api/columnManager/getColumnInfoPage',
          {params: {pageNum: this.currentPage, pageSize: this.pageSize, tableName:this.formData.tableName}}
      ).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.records
        }
      })
      this.tableShow = true
      this.loading =false
    },
    addData() {
      this.addTableShow = true
      request.get('/api/manager/getAllTableName').then(res => {
        if (res.code === '0') {
          res.data.forEach((rowData) => {
            this.allTableInfoInDb.push({"name": rowData.name, "label": rowData.label})
          })
        } else {
          alert("获取所有表信息错误！")
        }
      })
      this.mapForm = {}
    },
    addColumnItem() {
      this.changeTableName(this.formData.tableName)
      this.dialogColumnVisible = true
    },
    changeTableName(tableName) {
      request.get('/api/manager/getColumnInDb', {params: {tableName: tableName}}).then(res => {
        if (res.code === '0') {
          this.columnNames = res.data
        }
      })
    },
    load() {
      this.loading = true
      request.get('/api/columnManager/getColumnInfoPage',
          {params: {pageNum: this.currentPage, pageSize: this.pageSize, tableName:this.formData.tableName}}
      ).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.records
        }
      })
      this.tableShow = true
      this.loading =false
    },
    addTableInfo() {
      if (this.mapForm.id) {  // 更新
        request.put("/api/manager", this.mapForm).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      } else {  // 新增
        if (this.mapForm.tableName === []) {
          alert("表名不能为空")
        }
        request.post("/api/manager", this.mapForm).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
            this.addTableShow = false  // 关闭弹窗
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      }
      this.timer = setTimeout(()=>{   //设置延迟执行
        this.mapperData('admin')
      },1000);
      this.addTableShow = false  // 关闭弹窗
    },
    addColumnInfo() {
      if (this.mapColumnForm.id) {  // 更新
        request.put("/api/columnManager", this.mapColumnForm.columnInfo).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() // 刷新表格的数据
          this.dialogColumnVisible = false  // 关闭弹窗
        })
      } else {  // 新增
        console.log(this.mapColumnForm)
        this.mapColumnForm.columnInfo.tableName= this.formData.tableName
        request.post("/api/columnManager",
            this.mapColumnForm.columnInfo).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.dialogColumnVisible = false  // 关闭弹窗
          this.load() // 刷新表格的数据
        })
      }
      this.mapColumnForm.columnInfo = {}
    },
    handleEdit(row) {
      this.mapColumnForm.columnInfo = JSON.parse(JSON.stringify(row))
      this.dialogColumnVisible = true
    },
    handleDelete(id) {
      request.delete("/api/columnManager/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>

