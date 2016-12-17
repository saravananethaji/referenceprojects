window.onload = function() {

  // Get references to elements on the page.
  var form = document.getElementById('message-form');
  var messageField = document.getElementById('message');
  var messagesList = document.getElementById('messages');
  var socketStatus = document.getElementById('status');
  var closeBtn = document.getElementById('close');
  var startStusChk = document.getElementById('startStusChk');
  var stopStusChck= document.getElementById('stopStusChck');


  // Create a new WebSocket.
  var socket = new WebSocket('ws://localhost:8080/StatusCheckWeb/status');


  // Handle any errors that occur.
  socket.onerror = function(error) {
    console.log('WebSocket Error: ' + error);
  };


  // Show a connected message when the WebSocket is opened.
  socket.onopen = function(event) {
    socketStatus.innerHTML = 'Connected to: ' + event.target.url;
    socketStatus.className = 'open';
  };


  // Handle messages sent by the server.
  socket.onmessage = function(event) {
    var message = event.data;
    messagesList.innerHTML += '<li class="received"><span>Received:</span>' +
                               message + '</li>';
  };


  // Show a disconnected message when the WebSocket is closed.
  socket.onclose = function(event) {
    socketStatus.innerHTML = 'Disconnected from WebSocket.';
    socketStatus.className = 'closed';
  };


  // Send a message when the form is submitted.
  form.onsubmit = function(e) {
    e.preventDefault();

    // Retrieve the message from the textarea.
    var message = messageField.value;

    // Send the message through the WebSocket.
    socket.send(message);

    // Add the message to the messages list.
    messagesList.innerHTML += '<li class="sent"><span>Sent:</span>' + message +
                              '</li>';

    // Clear out the message field.
    messageField.value = '';

    return false;
  };


  startStusChk.onclick=function(e){
	  
	  var message = '{"action": "START_MON" ,"data":{ "msg":"Start monitor"}}';
	  socket.send(message);
  };
  
 stopStusChck.onclick=function(e){
	  
	  var message = '{"action": "STOP_MON" ,"data":{"msg":"Stop monitor"}}';
	  socket.send(message);
  };
  
  // Close the WebSocket connection when the close button is clicked.
  closeBtn.onclick = function(e) {
    e.preventDefault();

    // Close the WebSocket.
    socket.close();
    socket=null;

    return false;
  };

};
