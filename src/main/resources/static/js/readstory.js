function getScene(id){
    $.ajax({
        type: "GET",
        url: "/api/scene/get/"+id,
        headers: {login: "", password: ""},
        success: function (data) {
            $(".scene-title").text(data["title"]);
            $(".scene-text").text(data["description"]);
        }
    });

    $.ajax({
        type: "GET",
        url: "/api/scene/get/"+id+"/choices",
        headers: {login: "", password: ""},
        success: function (data) {
            console.log(data);
            if(data){
                for(var key in data){
                    $(".scene-choices").append("<div id='"+data[key]["id"]+"' targetedscene='"+data[key]["targetedScene"]["id"]+"' class='choice'>"+data[key]["name"]+"</div>");
                }
            }
        }
    });
}

$(document).ready(function(){
    getScene(window.location.search.split("?idscene=")[1]);
});

$(document).on("click", ".choice", function(){
    window.location = "readstory.html?idscene="+$(this).attr("targetedscene");
});