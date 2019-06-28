$(document).ready(function(){
    getAllAuthorStories();
});

function getAllAuthorStories(){
    $.ajax({
        type: "GET",
        url: "http://localhost:5366/api/story/get",
        headers: {login: "brenn", password: "admin"}, // TODO
        success: function(data){
            for(var key in data){
                if(data[key]["loginAuthor"] == "brenn") {
                    $(".stories-list").append("<div id='" + data[key]["id"] + "' class='col-lg-4 col-md-6 col-sm-12 story-container'><div class='story bg-dark-primary-color'></div><div class='story-text'></div></div>");
                    $(".stories-list #" + data[key]["id"] + " .story").css("background", data[key]["firstScene"]["imageUrl"]);
                    $(".stories-list #" + data[key]["id"] + " .story-text").html("<h4>"+data[key]["title"]+"</h4><p class='mb-2'>"+data[key]["description"]+"</p><button class='btn mb-4 secondary-color story-edit'>Editer</button>");

                    $("#" + data[key]["id"] + " .story").mouseenter(function () {
                        var parent = $(this).parent();
                        $(".story-text", parent).css("display", "block");

                        $(".story-text", parent).mouseleave(function () {
                            $(this).css("display", "none");
                        });
                    });
                }
            }
        },
        error: function(){
            throw new Error("Error loading stories.");
        }
    });
}

$(document).on("click", ".story-edit", function(){
	editStory($(this).parent().parent().attr("id"));
});

function editStory(id){
	window.location = "editstory.html?idstory="+id;
}