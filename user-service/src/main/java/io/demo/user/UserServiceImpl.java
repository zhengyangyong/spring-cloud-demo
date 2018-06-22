/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.demo.user;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl {
  private final String id = UUID.randomUUID().toString();

  @GetMapping(path = "/login")
  public String login(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password) {
    if ("zhangsan".equals(name) && "123456".equals(password)) {
      try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return "token = " + Hex.encodeHexString(digest.digest((name + format.format(new Date())).getBytes("utf-8")))
            + " from = " + id;
      } catch (Exception e) {
        return "Exception Occurred";
      }
    } else {
      return "Illegal Username or Password";
    }
  }
}