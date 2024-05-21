<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务种类" prop="stname">
        <el-input
          v-model="queryParams.stname"
          placeholder="请输入服务种类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店员" prop="clname">
        <el-input
          v-model="queryParams.clname"
          placeholder="请输入店员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物店" prop="msname">
        <el-input
          v-model="queryParams.msname"
          placeholder="请输入宠物店"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物" prop="pname">
        <el-input
          v-model="queryParams.pname"
          placeholder="请输入宠物名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务时间" prop="serstime">
        <el-date-picker clearable
          v-model="queryParams.serstime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择服务开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="服务评分" prop="serscore">
        <el-select v-model="queryParams.serscore" placeholder="请选择服务评分" clearable>
          <el-option
            v-for="dict in dict.type.sys_service_score"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务状态" prop="serstate">
        <el-select v-model="queryParams.serstate" placeholder="请选择服务状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_service_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:man_service:add']"
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
          v-hasPermi="['system:man_service:edit']"
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
          v-hasPermi="['system:man_service:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:man_service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="man_serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务记录编号" align="center" prop="serid" />
      <el-table-column label="服务种类" align="center" prop="stid" />
      <el-table-column label="店员" align="center" prop="clid" />
      <el-table-column label="宠物店" align="center" prop="mid" />
      <el-table-column label="宠物编号" align="center" prop="pid" />
      <el-table-column label="服务开始时间" align="center" prop="serstime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.serstime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务结束时间" align="center" prop="seretime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.seretime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务评分" align="center" prop="serscore">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_score" :value="scope.row.serscore"/>
        </template>
      </el-table-column>
      <el-table-column label="服务评价" align="center" prop="serassess" />
      <el-table-column label="服务状态" align="center" prop="serstate">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_state" :value="scope.row.serstate"/>
        </template>
      </el-table-column>
      <el-table-column label="服务花费" align="center" prop="sermoney" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:man_service:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:man_service:remove']"
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMan_service, getMan_service, delMan_service, addMan_service, updateMan_service } from "@/api/system/man_service";

export default {
  name: "Man_service",
  dicts: ['sys_service_state', 'sys_service_score'],
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
      // 订单管理表格数据
      man_serviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stid: null,
        clid: null,
        mid: null,
        pid: null,
        serstime: null,
        seretime: null,
        serscore: null,
        serassess: null,
        serstate: null,
        sermoney: null
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
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listMan_service(this.queryParams).then(response => {
        this.man_serviceList = response.rows;
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
        serid: null,
        stid: null,
        clid: null,
        mid: null,
        pid: null,
        serstime: null,
        seretime: null,
        serscore: null,
        serassess: null,
        serstate: null,
        sermoney: null
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
      this.ids = selection.map(item => item.serid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serid = row.serid || this.ids
      getMan_service(serid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serid != null) {
            updateMan_service(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMan_service(this.form).then(response => {
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
      const serids = row.serid || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + serids + '"的数据项？').then(function() {
        return delMan_service(serids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/man_service/export', {
        ...this.queryParams
      }, `man_service_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
