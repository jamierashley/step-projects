e = {
  origx: 11,
  origy: 10,
  d: 20,
  x: 11,
  y: 10,
  xvel: 8,
  flag: false
}
let pc = {
  radius: 50,
  startx: 0,
  stopx: 0,
  x: 26,
  y: 25,
  flag: true,
  xvel: 2
};


function setup() {
  canvas = createCanvas(windowWidth*0.973, 400);
  canvas.parent('skills-animation')
}

function draw() {
  background(20,20,20);
  fill(200)
  e.x += e.xvel;
  ellipse(e.x,e.y,e.d,e.d);
  check_border();
  
  make_line();
    noStroke();
  arc(pc.x,pc.y,pc.radius, pc.radius,pc.startx,pc.stopx);
  
  //Movement algorithm
  
  pacman();
  pacman_border()
  pc.x += pc.xvel;
}

function pacman_border(){
  if (pc.x >= windowWidth*0.973){
    pc.xvel *= -1;
    pc.y += 40;
  }else if(pc.x <=pc.radius/2){
    pc.xvel *= -1;
    pc.y += 40;
  }
  if (pc.y > 200){
    pc.y = 25;
  }
}
function make_line(){
  strokeWeight(20);
  line(e.origx,e.origy+e.d, e.x, e.y+e.d);
}

function check_border(){
//-------x-border--------------  
  if (e.x > windowWidth*0.973){
    e.flag = true;
    e.y += e.d;
  }
  if (e.x < 0){
    e.flag = false;
    e.y += e.d;
  }
  //-------------------------
  //-----------y border---------------
  if(e.y > 200)
    e.y = e.d+e.origy;
  if (e.flag)
    e.xvel = -8;
  else
    e.xvel = 8;
}

function pacman(){
  if(pc.startx >= 0.7)
    flag=false;
  else if(pc.startx <= 0)
    flag = true;
  if (flag){  
    pc.startx+= 0.03;
    pc.stopx -= 0.03;
  }else{
    pc.startx -= 0.03;
    pc.stopx += 0.03;
  }
}
function topFunction() {
  document.body.scrollTop = 0; // For Safari
  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}
