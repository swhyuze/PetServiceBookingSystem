<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="宠物编号" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入宠物编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物重量" prop="pstweight">
        <el-input
          v-model="queryParams.pstweight"
          placeholder="请输入宠物重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录时间">
        <el-date-picker
          v-model="daterangePsttime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="状态描述" prop="pstps">
        <el-input
          v-model="queryParams.pstps"
          placeholder="请输入状态描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:adm_petstate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:adm_petstate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:adm_petstate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_petstate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_petstateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="宠物健康记录编号" align="center" prop="pstid" />
      <el-table-column label="宠物编号" align="center" prop="pid" />
      <el-table-column label="宠物重量" align="center" prop="pstweight" />
      <el-table-column label="记录时间" align="center" prop="psttime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.psttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态描述" align="center" prop="pstps" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_petstate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_petstate:remove']"
          >删除</el-button>
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

    <!-- 添加或修改宠物健康对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="宠物编号" prop="pid">
          <el-input v-model="form.pid" placeholder="请输入宠物编号" />
        </el-form-item>
        <el-form-item label="宠物重量" prop="pstweight">
          <el-input v-model="form.pstweight" placeholder="请输入宠物重量" />
        </el-form-item>
        <el-form-item label="记录时间" prop="psttime">
          <el-date-picker clearable
            v-model="form.psttime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态描述" prop="pstps">
          <el-input v-model="form.pstps" placeholder="请输入状态描述" />
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
import { listAdm_petstate, getAdm_petstate, delAdm_petstate, addAdm_petstate, updateAdm_petstate } from "@/api/system/adm_petstate";

export default {
  name: "Adm_petstate",
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
      // 宠物健康表格数据
      adm_petstateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态描述时间范围
      daterangePsttime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pid: null,
        pstweight: null,
        psttime: null,
        pstps: null
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
    /** 查询宠物健康列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePsttime && '' != this.daterangePsttime) {
        this.queryParams.params["beginPsttime"] = this.daterangePsttime[0];
        this.queryParams.params["endPsttime"] = this.daterangePsttime[1];
      }
      listAdm_petstate(this.queryParams).then(response => {
        this.adm_petstateList = response.rows;
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
        pstid: null,
        pid: null,
        pstweight: null,
        psttime: null,
        pstps: null
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
      this.daterangePsttime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.pstid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宠物健康";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pstid = row.pstid || this.ids
      getAdm_petstate(pstid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物健康";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pstid != null) {
            updateAdm_petstate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_petstate(this.form).then(response => {
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
      const pstids = row.pstid || this.ids;
      this.$modal.confirm('是否确认删除宠物健康编号为"' + pstids + '"的数据项？').then(function() {
        return delAdm_petstate(pstids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_petstate/export', {
        ...this.queryParams
      }, `adm_petstate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
