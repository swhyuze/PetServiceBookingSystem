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
      <el-form-item label="顾客" prop="cuname">
        <el-input
          v-model="queryParams.cuname"
          placeholder="请输入顾客"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:clk_service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clk_serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务种类" align="center" prop="stname" />
      <el-table-column label="顾客" align="center" prop="cuname" />
      <el-table-column label="宠物" align="center" prop="pname" />
      <el-table-column label="服务开始时间" align="center" prop="serstime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.serstime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务结束时间" align="center" prop="seretime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.seretime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务评分" align="center" prop="serscore">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_service_score" :value="scope.row.serscore"/>
        </template>
      </el-table-column>
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
            icon="el-icon-check"
            @click="handleFinish(scope.row)"
          >完成</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:clk_service:edit']"
          >查看</el-button>
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

    <!-- 添加或修改订单查询对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务种类" prop="stname">
          <el-input v-model="form.stname" placeholder="请输入服务种类" :disabled="true"/>
        </el-form-item>
        <el-form-item label="店员" prop="clname">
          <el-input v-model="form.clname" placeholder="请输入店员" :disabled="true"/>
        </el-form-item>
        <el-form-item label="宠物店" prop="msname">
          <el-input v-model="form.msname" placeholder="请输入宠物店" :disabled="true"/>
        </el-form-item>
        <el-form-item label="宠物编号" prop="pname">
          <el-input v-model="form.pname" placeholder="请输入宠物编号" :disabled="true"/>
        </el-form-item>
        <el-form-item label="服务开始时间" prop="serstime" >
          <el-date-picker clearable
            v-model="form.serstime"
            :disabled="true"
            type="datetime"
            placeholder="请选择服务开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务结束时间" prop="seretime" >
          <el-date-picker clearable
            v-model="form.seretime"
            type="datetime"
            :disabled="true"
            placeholder="请选择服务结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务评分" prop="serscore">
          <el-select v-model="form.serscore" placeholder="请选择服务评分" :disabled="true">
            <el-option
              v-for="dict in dict.type.sys_service_score"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务评价" prop="serassess">
          <el-input v-model="form.serassess" placeholder="请输入服务评价" :disabled="true"/>
        </el-form-item>
        <el-form-item label="服务状态" prop="serstate">
          <el-select v-model="form.serstate" placeholder="请选择服务状态" :disabled="true">
            <el-option
              v-for="dict in dict.type.sys_service_state"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务花费" prop="sermoney">
          <el-input v-model="form.sermoney" placeholder="请输入服务花费" />
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
import { listClk_service, getClk_service, delClk_service, addClk_service, updateClk_service } from "@/api/system/clk_service";

export default {
  name: "Clk_service",
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
      // 订单查询表格数据
      clk_serviceList: [],
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
        stid: [
          { required: true, message: "服务种类不能为空", trigger: "blur" }
        ],
        clid: [
          { required: true, message: "店员不能为空", trigger: "blur" }
        ],
        mid: [
          { required: true, message: "宠物店不能为空", trigger: "blur" }
        ],
        pid: [
          { required: true, message: "宠物编号不能为空", trigger: "blur" }
        ],
        serstime: [
          { required: true, message: "服务开始时间不能为空", trigger: "blur" }
        ],
        seretime: [
          { required: true, message: "服务结束时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单查询列表 */
    getList() {
      this.loading = true;
      listClk_service(this.queryParams).then(response => {
        this.clk_serviceList = response.rows;
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
        sermoney: null,
        pname: null,
        stname: null,
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
      this.title = "新增订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serid = row.serid || this.ids
      getClk_service(serid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "订单信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serid != null) {
            updateClk_service(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClk_service(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleFinish(row) {
      const serids = row.serid || this.ids;
        getClk_service(serids).then(response => {
          this.form = response.data;
          if(this.form.serstate==0){
            this.form.serstate=2;
            updateClk_service(this.form);
            this.getList();
            this.$modal.msgSuccess("完成成功");
          }
          if(this.form.serstate==1){
            this.$modal.msgSuccess("已取消的订单无法完成");
          }
          if(this.form.serstate==2){
            this.$modal.msgSuccess("请勿重复完成");
          }
          this.reset();
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/clk_service/export', {
        ...this.queryParams
      }, `clk_service_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
