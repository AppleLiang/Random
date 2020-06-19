package com.test.type;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeTest {
  public static void main(String[] args) throws NoSuchMethodException, SecurityException {
    Method method =
        TypeTest.class.getMethod(
            "testType", List.class, List.class, List.class, List.class, List.class, Map.class);
    Type[] types = method.getGenericParameterTypes(); // 按照声明顺序返回 Type 对象的数组
    for (Type type : types) {
      ParameterizedType pType = (ParameterizedType) type; // 最外层都是ParameterizedType
      Type[] types2 = pType.getActualTypeArguments(); // 返回表示此类型【实际类型参数】的 Type 对象的数组
      for (int i = 0; i < types2.length; i++) {
        Type type2 = types2[i];
        System.out.println(
            i
                + "  类型【"
                + type2
                + "】\t类型接口【"
                + type2.getClass().getInterfaces()[0].getSimpleName()
                + "】");
      }
    }
  }

  public <T> void testType(
      List<String> a1,
      List<ArrayList<String>> a2,
      List<T> a3, //
      List<? extends Number> a4,
      List<ArrayList<String>[]> a5,
      Map<String, Integer> a6) {}
}
