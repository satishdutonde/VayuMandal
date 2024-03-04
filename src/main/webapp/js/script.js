		document.getElementById("searchBox").addEventListener("input", function() {
		  var query = this.value;
		  if (query.length > 2) {
			fetch("https://nominatim.openstreetmap.org/search?q=" + query + "&format=json")
			  .then(response => response.json())
			  .then(data => {
				var datalist = document.getElementById("places");
				datalist.innerHTML = "";
				data.forEach(function(place) {
				  var option = document.createElement("option");
				  option.value = place.display_name;
				  option.setAttribute("data-lat", place.lat);
				  option.setAttribute("data-lon", place.lon);
				  datalist.appendChild(option);
				});
			  });
		  } else {
			document.getElementById("places").innerHTML = "";
		  }
		});
	
		document.getElementById("searchBox").addEventListener("change", function() {
		  // Enable/disable submit button based on whether a place is selected
		  var selectedPlace = document.getElementById("searchBox").value;
		  var submitButton = document.getElementById("submitButton");
		  if (selectedPlace.trim() !== "") {
			submitButton.disabled = false; // Enable submit button
		  } else {
			submitButton.disabled = true; // Disable submit button
		  }
		});
	
		document.getElementById("searchBox").addEventListener("keydown", function(event) {
		  if (event.key === "Enter") {
			event.preventDefault(); // Prevent default form submission for now
	
			// Get the selected place details
			var selectedPlace = document.getElementById("searchBox").value;
			var selectedOption = document.querySelector("#places option[value='" + selectedPlace + "']");
			var lat = selectedOption.dataset.lat;
			var lon = selectedOption.dataset.lon;
	
			// Set the latitude and longitude in hidden fields
			document.getElementById("lat").value = lat;
			document.getElementById("lon").value = lon;
	
			// Submit the form
			document.getElementById("placeForm").submit();
		  }
		});
	
		document.getElementById("placeForm").addEventListener("submit", function(event) {
		  // Prevent default form submission
		  event.preventDefault();
	
		  // Submit the form
		  this.submit();
		});