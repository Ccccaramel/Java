Q:get请求，一个参数是时间格式的字符串(2022-07-04T18:00:00)
  后台用一个 LocalDateTime 的变量接收
  请求失败,报 400 错误,原本以为是特殊字符的原因,但发现不是
  猜测是 key 的 name 与 data 内定义的变量名同名的原因,很明显也不是
  于是修改 key ,发现只要不是原来的 name 就都可以请求成功
  一直没有去猜测是后台参数接收的原因,我以为 400 就是前台的问题
  直到我把后台的参数名改了才发现是后台接收参数的类型有问题
A:仔细想了想,这是一个很低级的自以为是的错误
  前端请求的数据是以字符串形式传过来的
  之所以 Integer 可以是因为它是包装类型,自动装箱
  而 LocalDateTime 则没有,你需要修改 get 方法
  通过 LocalDateTime.parse(settlementTime) 进行转换
  如果后台参数类型不是 Integer 而是 int
    1.单个字符
      存储该字符的 ASCII 码
    1.字符串
      可能与 LocalDateTime 一样(没测)

Q:同一浏览器多用户登陆问题
A:限制只允许一个用户登录