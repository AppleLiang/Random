package com.test.guice;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MultibindingsScanner;
import com.google.inject.name.Names;
import com.google.inject.util.Types;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

  public static void main(String[] args) {
    Injector injector =
        Guice.createInjector(
            new ImmutableList.Builder<Module>()
                .add(MultibindingsScanner.asModule())
                .add(new SetsModule())
                .add(new StringsModule())
                .build());

    // this won't work since binding key is typeliteral and annotation
    //    Key<Set> key = Key.get(Set.class, Names.named("set2"));
    //    Set<TestInterface> set2 = injector.getInstance(key);

    // this will work
    Key<Set<TestInterface>> key =
        (Key<Set<TestInterface>>)
            Key.get(TypeLiteral.get(Types.setOf(TestInterface.class)), Names.named("set2"));
    Set<TestInterface> set2 = injector.getInstance(key);

    // You can still filter by raw type, you will get two sets
    Map<Key<?>, Binding<?>> map =
        injector.getAllBindings().entrySet().stream()
            .filter(
                keyBindingEntry ->
                    keyBindingEntry.getKey().getTypeLiteral().getRawType().equals(Set.class))
            .collect(ImmutableMap.toImmutableMap(Entry::getKey, Entry::getValue));

    System.out.println(map);
  }
}
