$(document).ready(function(){
	//getAllStories();
	$(".story").hover(function(){
		var parent = $(this).parent();
		$(".story-text", parent).css("display","block");
	});

	$(".story-text").mouseleave(function(){
		$(this).css("display","none");
	});
});

function getAllStories(){
	$.ajax({
		method: "GET",
		url: "/get/allstories",
		success: function(data){
			for(var key in data){
				$(".stories-list").append("<div id='" + data[key][idStory] + "' class='col-lg-4 col-md-6 col-sm-12 story-container'><div class='story'></div><div class='story-text'></div></div>");
				$(".stories-list #" + data[key][idStory] + " .story").css("background", data[key][imgStory] + "  no-repeat center fixed").css("background-size", "cover");
				$(".stories-list #" + data[key][idStory] + " .story-text").text(data[key][txtStory]);
			}
		},
		error: function(){
			alert("Erreur au chargement des histoires.");
			throw new error("Error loading stories.");
		}
	});
}