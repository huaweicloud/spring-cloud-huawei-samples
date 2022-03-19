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
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);
  }

  @Test
  // 要求：接口通过基类定义，需要得到子类信息。为了满足这个要求，需要在基类增加 @JsonTypeInfo
  public void testBase() throws Exception {
    ChildBase base = new ChildBase();
    base.setAge("a");
    base.setName("n");

    String se = objectMapper.writeValueAsString(base);
    Assert.assertEquals("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBase\",\"name\":\"n\",\"age\":\"a\"}",
        se);

    Base after = objectMapper.readValue(se, Base.class); // 基类没有类型信息， 得到子类信息
    Assert.assertTrue(after instanceof ChildBase);
    Assert.assertEquals(((ChildBase) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    after = objectMapper.readValue(se, ChildBase.class); // 子类只能得到子类
    Assert.assertTrue(after instanceof ChildBase);
    Assert.assertEquals(((ChildBase) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    // 要求： 对于前端请求的场景，不会传递 @class 信息。 需要能够解析这种场景，目前前端都是确定的子类。
    after = objectMapper.readValue("{\"name\":\"n\",\"age\":\"a\"}", ChildBase.class);
    Assert.assertTrue(after instanceof ChildBase);
    Assert.assertEquals(((ChildBase) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    // 要求：本地不存在的子类，使用基类
    after = objectMapper
        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
            Base.class);
    Assert.assertTrue(!(after instanceof ChildBase));
    Assert.assertEquals(after.getName(), "n");

    // 要求：本地不存在的子类，使用子类
    after = objectMapper
        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
            ChildBase.class);
    Assert.assertTrue(after instanceof ChildBase);
    Assert.assertEquals(((ChildBase) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");
  }

  @Test
  // 要求：子类加上 @JsonTypeInfo， 基类没加上的场景
  public void testBase2() throws Exception {
    ChildBase2 base = new ChildBase2();
    base.setAge("a");
    base.setName("n");

    String se = objectMapper.writeValueAsString(base);
    Assert.assertEquals("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBase2\",\"name\":\"n\",\"age\":\"a\"}",
        se);

    Base2 after = objectMapper.readValue(se, Base2.class); // 基类没有类型信息， 只能得到基类
    Assert.assertTrue(!(after instanceof ChildBase2));
    Assert.assertEquals(after.getName(), "n");

    after = objectMapper.readValue(se, ChildBase2.class); // 子类只能得到子类
    Assert.assertTrue(after instanceof ChildBase2);
    Assert.assertEquals(((ChildBase2) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    // 要求： 对于前端请求的场景，不会传递 @class 信息。 需要能够解析这种场景，目前前端都是确定的子类。
    after = objectMapper.readValue("{\"name\":\"n\",\"age\":\"a\"}", ChildBase2.class);
    Assert.assertTrue(after instanceof ChildBase2);
    Assert.assertEquals(((ChildBase2) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    // 要求：本地不存在的子类，使用基类
    after = objectMapper
        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
            Base2.class);
    Assert.assertTrue(!(after instanceof ChildBase2));
    Assert.assertEquals(after.getName(), "n");

    // 要求：本地不存在的子类，使用子类
    after = objectMapper
        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
            ChildBase2.class);
    Assert.assertTrue(after instanceof ChildBase2);
    Assert.assertEquals(((ChildBase2) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");
  }

  @Test
  // 要求：接口通过基类定义，需要得到子类信息。为了满足这个要求，需要在基类增加 @JsonTypeInfo
  public void testBase3() throws Exception {
    ChildBase3 base = new ChildBase3();
    base.setAge("a");
    base.setName("n");

    String se = objectMapper.writeValueAsString(base);
    Assert.assertEquals("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBase3\",\"name\":\"n\",\"age\":\"a\"}",
        se);

    Base3 after = objectMapper.readValue(se, Base3.class); // 基类没有类型信息， 得到子类信息
    Assert.assertTrue(after instanceof ChildBase3);
    Assert.assertEquals(((ChildBase3) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

    after = objectMapper.readValue(se, ChildBase3.class); // 子类只能得到子类
    Assert.assertTrue(after instanceof ChildBase3);
    Assert.assertEquals(((ChildBase3) after).getAge(), "a");
    Assert.assertEquals(after.getName(), "n");

//    // 要求： 对于前端请求的场景，不会传递 @class 信息。 需要能够解析这种场景，目前前端都是确定的子类。
//    after = objectMapper.readValue("{\"name\":\"n\",\"age\":\"a\"}", ChildBase3.class);
//    Assert.assertTrue(after instanceof ChildBase3);
//    Assert.assertEquals(((ChildBase3) after).getAge(), "a");
//    Assert.assertEquals(after.getName(), "n");

    // 要求：本地不存在的子类，使用基类
    after = objectMapper
        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
            Base3.class);
    Assert.assertTrue(!(after instanceof ChildBase3));
    Assert.assertEquals(after.getName(), "n");

//    // 要求：本地不存在的子类，使用子类
//    after = objectMapper
//        .readValue("{\"@class\":\"com.alibaba.edas.serialization.test.ChildBasex\",\"name\":\"n\",\"age\":\"a\"}",
//            ChildBase3.class);
//    Assert.assertTrue(after instanceof ChildBase3);
//    Assert.assertEquals(((ChildBase3) after).getAge(), "a");
//    Assert.assertEquals(after.getName(), "n");
  }

  @Test
  // 要求：接口定义使用泛型。 需要在泛型属性上面加上 @JsonTypeInfo
  public void testGeneric() throws Exception {
    ChildBase base = new ChildBase();
    base.setAge("a");
    base.setName("n");

    Generic<ChildBase> generic = new Generic<>();
    List<ChildBase> data = new ArrayList<>();
    data.add(base);
    generic.setData(data);

    String se = objectMapper.writeValueAsString(generic);
    Assert.assertEquals(
        "{\"data\":[{\"@class\":\"com.alibaba.edas.serialization.test.ChildBase\",\"name\":\"n\",\"age\":\"a\"}]}", se);

    Generic<ChildBase> after = objectMapper.readValue(se, new TypeReference<Generic<ChildBase>>() {
    });
    Assert.assertEquals(after.getData().get(0).getAge(), "a");
    Assert.assertEquals(after.getData().get(0).getName(), "n");

    Generic<Base> after2 = objectMapper.readValue(se, new TypeReference<Generic<Base>>() {
    });
    Assert.assertTrue(after2.getData().get(0) instanceof ChildBase);
    Assert.assertEquals(after2.getData().get(0).getName(), "n");
  }
}
