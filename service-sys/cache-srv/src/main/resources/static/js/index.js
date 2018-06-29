$(function(){

	$("#redisSearch").click(function(){
        var redisKey = $("#redisKey").val();
        if(redisKey == undefined || redisKey == null || redisKey == ""){
            $("#tips").empty().append("redis key is not empty!");
            return;
        }
        $("#tips").empty();
        var keyType = $("#keyType").val();
        var serializeFlag = $("input[name='serializeFlag']:checked").val();
        $.ajax({
            url: 'serializeUtilCache/get',
			data: {"redisKey":redisKey,"keyType":keyType,"serializeFlag":serializeFlag},
            type: "GET",
            success: function(data){
                $("#redisValue").text(data.model.jsonStr);
            },
            error: function(){
                alert("error");
            }
        })
    });
});