$(document).ready(function(){
	getAllStories();	
});

function getAllStories(){
	$.ajax({
		type: "GET",
		url: "http://localhost:5366/api/story/get",
		headers: {login: "", password: ""},
		success: function(data){
			for(var key in data){
				$(".stories-list").append("<div id='"+data[key]["id"]+"' firstscene='"+data[key]["firstScene"]["id"]+"' class='col-lg-4 col-md-6 col-sm-12 story-container'><div class='story bg-dark-primary-color'></div><div class='story-text'></div></div>");
				$(".stories-list #" + data[key]["id"] + " .story").css("background", data[key]["firstScene"]["imageUrl"]);
				$(".stories-list #" + data[key]["id"] + " .story-text").html("<h4>"+data[key]["title"]+"</h4><p class='mb-2'>"+data[key]["description"]+"</p><button class='btn mb-4 secondary-color story-read'>Lire</button>");

				$("#" + data[key]["id"] + " .story").mouseenter(function(){
					var parent = $(this).parent();
					$(".story-text", parent).css("display","block");

					$(".story-text", parent).mouseleave(function(){
						$(this).css("display","none");
					});
				});

				$("#"+data[key]["id"]+" .story-read").click(function(){
					readStory($("#"+data[key]["id"]).attr("firstscene"));
				});
			}
		},
		error: function(){
			throw new Error("Error loading stories.");
		}
	});
}

function readStory(idscene){
	window.location = "readstory.html?idscene="+idscene;
}