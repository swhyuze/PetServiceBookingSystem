<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="上传时间" prop="proutime">
        <el-date-picker clearable
          v-model="queryParams.proutime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择问题上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="回复时间" prop="prortime">
        <el-date-picker clearable
          v-model="queryParams.prortime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择回复时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="反馈人员" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入问题反馈人员账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="adm_problemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="问题编号" align="center" prop="proid" />
      <el-table-column label="问题描述" align="center" prop="protext" />
      <el-table-column label="问题回复" align="center" prop="prore" />
      <el-table-column label="上传时间" align="center" prop="proutime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proutime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="反馈时间" align="center" prop="prortime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.prortime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="问题反馈人员账号" align="center" prop="uid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_problem:edit']"
          >回复</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改问题反馈对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="问题描述">
          <el-input v-model="form.protext" :min-height="192" :disabled="true"/>
        </el-form-item>
        <el-form-item label="上传时间" prop="proutime" :disabled="true">
          <el-date-picker clearable
            v-model="form.proutime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择问题上传时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="问题回复" prop="prore">
          <el-input v-model="form.prore" placeholder="请输入问题回复" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAdm_problem, getAdm_problem, delAdm_problem, addAdm_problem, updateAdm_problem } from "@/api/system/adm_problem";

export default {
  name: "Adm_problem",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 问题反馈表格数据
      adm_problemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        protext: null,
        prore: null,
        proutime: null,
        prortime: null,
        uid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询问题反馈列表 */
    getList() {
      this.loading = true;
      listAdm_problem(this.queryParams).then(response => {
        this.adm_problemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        proid: null,
        protext: null,
        prore: null,
        proutime: null,
        prortime: null,
        uid: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.proid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加问题反馈";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const proid = row.proid || this.ids
      getAdm_problem(proid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改问题反馈";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.proid != null) {
            updateAdm_problem(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_problem(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const proids = row.proid || this.ids;
      this.$modal.confirm('是否确认删除问题反馈编号为"' + proids + '"的数据项？').then(function() {
        return delAdm_problem(proids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_problem/export', {
        ...this.queryParams
      }, `adm_problem_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
