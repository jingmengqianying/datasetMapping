<template>
  <div>
    <div>
      <el-form :model="formData" label-width="120px" class="demo-ruleForm" status-icon>
        <el-form-item label="表名" prop="tableName" :rules="rules.tableName">
          <el-select v-model="formData.tableName" class="m-2" placeholder="请选择">
            <el-option v-for="item in tableInfo" :key="item.name" :label="item.label" :value="item.name"/>
          </el-select>
        </el-form-item>

        <el-form-item label="字段名" prop="columnName">
          <el-checkbox-group v-model="formData.columnList">
            <el-checkbox v-for="item in columnNames" :label="item.name" :key="item.name" :value="item.name" border>
              {{ item.label }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="查询条件" prop="condition">
          <el-button class="primary" @click="onAddItem">增加查询条件</el-button>
          <br>
          <div v-for="(condition, index) in formData.conditions" :key="condition.name">
            <el-select v-model="condition.name" class="m-2" placeholder="请选择字段名">
              <el-option v-for="item in columnNames" :key="item.name" :label="item.label" :value="item.name"/>
            </el-select>
            <el-select v-model="condition.type" class="m-2" placeholder="过滤类型">
              <el-option v-for="item in conditionType" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
            <el-input v-model="condition.value" placeholder="值" style="width:200px"/>
            <el-button class="btn2" type="danger" @click="deleteItem(condition, index)">删除
            </el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-button type="primary" @click="queryData">查询</el-button>
    </div>
    <div v-show="tableShow">
      <el-table :data="tableData" stripe style="width: 100%" height="250" v-loading="loading">
        <el-table-column v-for="(item, index) in columnList" :key="index"
                         :label="item.label" :prop="item.name"/>
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
import {reactive} from 'vue'
import request from '@/utils/request'

const rules = reactive({
  tableName: [{required: true, message: '请选择表名', trigger: 'blur',}]
})

const conditionType = [
  {
    value: '>',
    label: '大于',
  },
  {
    value: '<',
    label: '小于',
  },
  {
    value: '=',
    label: '等于',
  },
  {
    value: '!=',
    label: '不等于',
  },
  {
    value: 'like',
    label: '模糊匹配',
  }
]

export default {
  name: 'HomePage',
  components: {},
  data() {
    return {
      rules: rules,
      formData: {
        tableName: '',
        columnList: [],
        conditions: [{name: "", type: "", value: ""}],
      },
      tableInfo: [],
      columnNames: [],
      conditionType: conditionType,
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
    'formData.tableName': { // 对对象进行深度监听
      handler(newValue) {
        request.get('/api/columnManager/getColumnInfoByTableName', {params: {tableName: newValue}}).then(res => {
          if (res.code === '0') {
            this.columnNames = res.data
          }
        })
      },
      immediate: false,
      deep: true
    }
  },
  methods: {
    onAddItem() {
      this.formData.conditions.push({"name": "", "type": "", "value": ""});
    },
    deleteItem(item, index) {
      this.formData.conditions.splice(index, 1);
    },
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
    checkFromData() {
      if (this.formData.tableName === '') {
        alert("请选择表名");
      }
    },
    queryData() {
      this.checkFromData()
      request.post('/api/manager/getBySql',
          this.formData, {params: {pageNum: this.currentPage, pageSize: this.pageSize}})
          .then(res => {
            if (res.code !== '0') {
              alert("查询失败")
            }
            this.columnList = []
            if (this.formData.columnList === undefined || this.formData.columnList.length <= 0) {
              this.columnList = this.columnNames;
            } else {
              this.columnNames.forEach(item => {
                if (this.formData.columnList.includes(item.name)) {
                  this.columnList.push(item);
                }
              })
            }
            this.tableData = res.data.records
            this.total = res.data.total
            this.tableShow = true
            this.loading = false
          })
    },
    load() {
      this.loading = true
      request.post('/api/manager/getBySql',
          this.formData, {params: {pageNum: this.currentPage, pageSize: this.pageSize}})
          .then(res => {
            this.loading = false
            if (res.code !== '0') {
              alert("查询失败")
            }
            this.tableData = res.data.records
            this.total = res.data.total
            this.tableShow = true
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
