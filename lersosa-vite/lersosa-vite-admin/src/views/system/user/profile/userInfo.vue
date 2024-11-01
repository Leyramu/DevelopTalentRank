<!--
  - Copyright (c) 2024 Leyramu. All rights reserved.
  - This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
  - For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
  - The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
  - By using this project, users acknowledge and agree to abide by these terms and conditions.
  -->

<template>
  <el-form ref="userRef" :model="form" :rules="rules" label-width="80px">
    <el-form-item label="用户昵称" prop="nickName">
      <el-input v-model="form.nickName" maxlength="30"/>
    </el-form-item>
    <el-form-item label="手机号码" prop="phonenumber">
      <el-input v-model="form.phonenumber" maxlength="11"/>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="form.email" maxlength="50"/>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="form.sex">
        <el-radio label="0">男</el-radio>
        <el-radio label="1">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">保存</el-button>
      <el-button type="danger" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {updateUserProfile} from "@/api/system/user";

const props = defineProps({
  user: {
    type: Object
  }
});

const {proxy} = getCurrentInstance();

const form = ref({});
const rules = ref({
  nickName: [{required: true, message: "用户昵称不能为空", trigger: "blur"}],
  email: [{required: true, message: "邮箱地址不能为空", trigger: "blur"}, {
    type: "email",
    message: "请输入正确的邮箱地址",
    trigger: ["blur", "change"]
  }],
  phonenumber: [{
    required: true,
    message: "手机号码不能为空",
    trigger: "blur"
  }, {pattern: /^1[3|456789][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}],
});

/** 提交按钮 */
function submit() {
  proxy.$refs.userRef.validate(valid => {
    if (valid) {
      updateUserProfile(form.value).then(_response => {
        proxy.$modal.msgSuccess("修改成功");
        props.user.phonenumber = form.value.phonenumber;
        props.user.email = form.value.email;
      });
    }
  });
}

/** 关闭按钮 */
function close() {
  proxy.$tab.closePage();
}

// 回显当前登录用户信息
watch(() => props.user, user => {
  if (user) {
    form.value = {nickName: user.nickName, phonenumber: user.phonenumber, email: user.email, sex: user.sex};
  }
}, {immediate: true});
</script>
