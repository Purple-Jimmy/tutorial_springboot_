### Autowired注解
默认情况下Autowired注解必须要能找到对应的对象,否则会报错.可以使用@Autowired(required=false)解决

## Autowired注解寻找bean的方式
1. 首先按照依赖对象的类型找,如果找到就使用setter或者字段直接注入
2. 如果在spring上下文中找到多个匹配的类型,再按照名字去找,如果找不到则报错

## @Qualifier注解
