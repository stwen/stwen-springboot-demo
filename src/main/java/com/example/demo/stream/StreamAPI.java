package com.example.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: jdk8 流
 * https://www.cnblogs.com/wuhenzhidu/p/10740091.html
 * @author: xianhao_gan
 * @date: 2020/08/12
 **/
public class StreamAPI {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("欧阳雪", 18, "中国", 'F'));
        personList.add(new Person("Tom", 24, "美国", 'M'));
        personList.add(new Person("Harley", 22, "英国", 'F'));
        personList.add(new Person("向天笑", 20, "中国", 'M'));
        personList.add(new Person("李康", 22, "中国", 'M'));
        personList.add(new Person("小梅", 20, "中国", 'F'));
        personList.add(new Person("何雪", 21, "中国", 'F'));
        personList.add(new Person("李康", 22, "中国", 'M'));

        System.out.println("筛选大于18岁的人：");
        personList.stream().filter(person -> person.getAge()>18).forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        System.out.println("找出所有中国人的数量：");
        long count = personList.stream().filter(person -> person.getCountry().equals("中国")).count();
        System.out.println(count);
        System.out.println("------------------------------------------------------------");


        System.out.println("按国籍分组：");// key:国籍，value:对应的list记录
        Map<String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(Person::getCountry));
        System.out.println(collect);
        System.out.println("------------------------------------------------------------");

        /**
         * ------------------------------------------------------------------------------------
         * Stream中间操作--筛选与切片
         * filter：接收Lambda，从流中排除某些操作；
         * limit：截断流，使其元素不超过给定对象
         * skip(n)：跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
         * distinct：筛选，通过流所生成元素的hashCode()和equals()去除重复元素。
         * ------------------------------------------------------------------------------------
         */
        System.out.println("从Person列表中取出两个女性：");
        personList.stream().filter(person -> person.getSex()=='F').limit(2).forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        System.out.println("从Person列表中从第2个女性开始，取出所有的女性：");
        personList.stream().filter(person -> person.getSex()=='F').skip(1).forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        System.out.println("去除男性中的重复记录--李康：");
        personList.stream().filter(person -> person.getSex()=='M').distinct().forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        /** ---------------------------------------------------------------------------------------------------------
         * Stream中间操作--映射
         * map--接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
         * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
         * ----------------------------------------------------------------------------------------------------------
         */

        // map-->接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        System.out.println("我们用一个PersonCountry类来接收所有的国家信息(去重))：");
        personList.stream().map(person -> {
            PersonCountry countryName = new PersonCountry();
            countryName.setCountry(person.getCountry());
            return countryName;
        }).distinct().forEach(System.out::println);
        System.out.println("------------------------------------------------------------");


        //按年龄排序，年靓相同则按姓名排序
        System.out.println("按年龄排序，年靓相同则按姓名排序:");
        Stream<Person> stream = personList.stream().sorted((p1, p2) -> {
            if (p1.getAge().equals(p2.getAge())) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getAge().compareTo(p2.getAge());
            }
        });
        stream.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        //判断是否全是成年人
        boolean b = personList.stream().allMatch(person -> person.getAge() >= 18);
        System.out.println("判断是否全是成年人:"+b);
        System.out.println("------------------------------------------------------------");

        //获取年龄最大与最小的人
        Optional<Person> maxAgePerson = personList.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        System.out.println("年龄最大者的信息："+maxAgePerson.get());
        Optional<Person> minAgePerson = personList.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        System.out.println("年龄最小者的信息："+minAgePerson.get());
        System.out.println("------------------------------------------------------------");
    }

}
