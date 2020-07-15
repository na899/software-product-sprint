var choice = document.getElementById("choice");

var randomFacts = ["I have 27 cousins, i think... oh wait, i might have missed one, let me count again [story of my life :'( ]","People seem to love giving me nicknames and the weirdest one of it is Navbar", "I can eat a whole boiled egg at once(and yes I've chocked on it at times, so dont try it out!","In my free time i like staring at the sky(the backgroud picture was taken by me during one such cloud watching session :D)"]
var output =  document.getElementById("output");


//processing commands from the terminal
choice.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {  
	var text = e.target.value;
	switch(text){
		case "skills":{
			output.innerHTML = "Skills :  <br> Programming languages : <br> C++, C, Javascript,Python <br> Other Technical skills: <br>Git, HTML/CSS , Bootstrap, Node.js, Express.js, AngularJS, React, SQL,  MongoDB, AWS"
			break;
		}
		case "random-fact":{
			output.innerHTML ="Here's a random fact about me:<br>"+randomFacts[Math.floor(Math.random() * randomFacts.length)];
			break;
		}
		case "work-exp":{
			output.innerHTML ="I interned as a SDE intern at Amazon during Summer of 2020, and at Tech Mahindra in the Summer of 2019. I have worked in various projects in Hackathons, as a part of Spider the R&D Club of NIT-Trichy and personal projects as well. Check out my resume and online profiles linked on the left to know more!"
			break;
		}
		case "clear":{
			output.innerHTML=""
			break;
		}
		case "help":{
			output.innerHTML = "Commands :<br>clear: to clear screen <br>help: to see valid commands <br>random-fact: to see a random fact about me<br>skills: know my technical skills"
			break;
		}
		 default:{
			output.innerHTML = "Invalid choice, use 'help' to know more about the commands ";
		}
	
	}
	choice.value='';
}
});



//typewriting effect
var TxtRotate = function(el, toRotate, period) {
  this.toRotate = toRotate;
  this.el = el;
  this.loopNum = 0;
  this.period = parseInt(period, 10) || 2000;
  this.txt = '';
  this.tick();
  this.isDeleting = false;
};

TxtRotate.prototype.tick = function() {
  var i = this.loopNum % this.toRotate.length;
  var fullTxt = this.toRotate[i];

  if (this.isDeleting) {
    this.txt = fullTxt.substring(0, this.txt.length - 1);
  } else {
    this.txt = fullTxt.substring(0, this.txt.length + 1);
  }

  this.el.innerHTML = '<span class="wrap">'+this.txt+'</span>';

  var that = this;
  var delta = 300 - Math.random() * 100;

  if (this.isDeleting) { delta /= 2; }

  if (!this.isDeleting && this.txt === fullTxt) {
    delta = this.period;
    this.isDeleting = true;
  } else if (this.isDeleting && this.txt === '') {
    this.isDeleting = false;
    this.loopNum++;
    delta = 500;
  }

  setTimeout(function() {
    that.tick();
  }, delta);
};

window.onload = function() {
  var elements = document.getElementsByClassName('txt-rotate');
  for (var i=0; i<elements.length; i++) {
    var toRotate = elements[i].getAttribute('data-rotate');
    var period = elements[i].getAttribute('data-period');
    if (toRotate) {
      new TxtRotate(elements[i], JSON.parse(toRotate), period);
    }
  }
  // INJECT CSS
  var css = document.createElement("style");
  css.type = "text/css";
  css.innerHTML = ".txt-rotate > .wrap { border-right: 0.08em solid #666 }";
  document.body.appendChild(css);
};



