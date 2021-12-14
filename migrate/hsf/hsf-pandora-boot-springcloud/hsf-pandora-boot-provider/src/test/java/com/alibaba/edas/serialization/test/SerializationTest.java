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

package com.alibaba.edas.serialization.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {
  private static ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  // 要求：接口通过基类定义，需要得到子类信息。为了满足这个要求，需要在基类增加 @JsonTypeInfo
  public void testBase() throws Exception {
    Base2 base = new Base2();
    base.setAge("a");
    base.setName("n");

    String se = objectMapper.writeValueAsString(base);
    System.out.println(se);

    Base after = objectMapper.readValue(se, Base.class);
    Assert.assertTrue(after instanceof Base2);
    Assert.assertEquals(((Base2) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");
  }

  @Test
  // 要求：接口定义使用泛型。 需要在泛型属性上面加上 @JsonTypeInfo
  public void testGeneric() throws Exception {
    Base2 base = new Base2();
    base.setAge("a");
    base.setName("n");

    Generic<Base2> generic = new Generic<>();
    List<Base2> data = new ArrayList<>();
    data.add(base);
    generic.setData(data);

    String se = objectMapper.writeValueAsString(generic);
    System.out.println(se);

    Generic<Base2> after = objectMapper.readValue(se, new TypeReference<Generic<Base2>>() {
    });
    Assert.assertEquals(after.getData().get(0).getAge(), "a");
    Assert.assertEquals(after.getData().get(0).getName(), "n");

    Generic<Base> after2 = objectMapper.readValue(se, new TypeReference<Generic<Base>>() {
    });
    Assert.assertTrue(after2.getData().get(0) instanceof Base2);
    Assert.assertEquals(after2.getData().get(0).getName(), "n");
  }
}
