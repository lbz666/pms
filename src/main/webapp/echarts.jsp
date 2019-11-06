<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/30
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var names=[];
            var values=[];

            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/echart/data",
                success:function (msg) {
                    for(key in msg){
                        names.push(key);
                        values.push(msg[key]);
                    }
                    // 基于准备好的dom，初始化echarts实例
                    var chart = echarts.init(document.getElementById('main'));
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '个操作系统手机销量情况展示',
                            subtext:'这是副文本标题',
                            top:'bottom',
                            left:'center',
                            textStyle:{
                                color:'#ccc',
                                fontSize:30
                            }

                        },
                        xAxis: {
                            data:names
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'bar',
                            data: values
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    chart.setOption(option);
                }

            });


        }
    </script>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>


</body>
</html>
