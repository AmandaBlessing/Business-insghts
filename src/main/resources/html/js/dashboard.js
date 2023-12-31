const purpose = document.getElementById('purpose');
const performence = document.getElementById('performence');
const conformance = document.getElementById('conformance');
const sustainability = document.getElementById('sustainability');

const pie = document.querySelectorAll(".pie");
const bar = document.querySelectorAll(".bar");
const line = document.querySelectorAll(".line");


const printDoc = document.querySelector("#print")


printDoc.addEventListener('click',()=>{
  window.print()
})








  
// purpose

bar[0].addEventListener('click', e => {
    
  BarChart(purpose);
});

pie[0].addEventListener('click', e => {
  
PieChart(purpose);
});

line[0].addEventListener('click', e => {
  
lineChart(purpose);
});

// performance

bar[1].addEventListener('click', e => {
    
  BarChart(performence);
});

pie[1].addEventListener('click', e => {
  
PieChart(performence);
});


line[1].addEventListener('click', e => {
  
lineChart(performence);
});

// conformance
bar[2].addEventListener('click', e => {
    
  BarChart(conformance);
});

pie[2].addEventListener('click', e => {
  
PieChart(conformance);
});

line[2].addEventListener('click', e => {
  
lineChart(conformance);
});

// susbstance
bar[3].addEventListener('click', e => {
    
  BarChart(sustainability);
});

pie[3].addEventListener('click', e => {
  
PieChart(sustainability);
});

line[3].addEventListener('click', e => {
  
lineChart(sustainability);
});

function PieChart(segment) {
  updateCanvas(segment, 'pie', {
    data: {
      datasets: [{
        data: [3, 1, 2, 3],
        backgroundColor: ['#CB4335', '#1F618D', '#F1C40F', '#27AE60']
      }]
    }
  });
}

function BarChart(segment) {
  updateCanvas(segment, 'bar', {
    data: {
      labels: ['', '', '', ''],
      datasets: [{
        label: 'Purpose',
        data: [3, 1, 2, 3, 4],
        backgroundColor: 'rgb(218,55,50)'
      }]
    }
  });
}

function lineChart(segment) {
  updateCanvas(segment, 'line', {
    data: {
      labels: ["", "", "", ""],
      datasets: [{
        label: 'My First Dataset',
        data: [3, 1, 2, 3, 4],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 1
      }]
    }
  });
}

function updateCanvas(canvas, type, chartOptions) {
  // Clear existing chart
  if (canvas.chart) {
    canvas.chart.destroy();
  }

  // Create new chart
  canvas.chart = new Chart(canvas, {
    type: type,
    data: chartOptions.data,
    options: chartOptions.options
  });
}

// Initial chart on load
BarChart(purpose);
BarChart(performence);
BarChart(conformance);
BarChart(sustainability);

const tdpurpose = document.querySelector("tdpurpose")
const  tdperformence= document.querySelector("tdperformence")
const tdcomformance = document.querySelector("tdcomformance")
const tdsubstainablility= document.querySelector("tdsubstainablility")
CreateColumn()
const stages = ["Highest","Moderate","Lowest"]
function CreateColumn(){
  let randomIdx = Math.floor(Math.random()*3)
  for(let i=0 ; i<12 ; i++){
  const td = document.createElement('td')
  if(randomIdx== 0){
    td.style = "#df1f1f"

  }
  else if (randomIdx==1){
    td.style = "rgb(255,165,0)"
    
  }
  else{
    td.style = "#53d053"
  }
  
  td.innerHTML = stages[randomIdx]

  if(i < 3){
    tdpurpose.appendChild(td)
  }else if(i>3 && i<6){
    tdperformence.appendChild(td)
  }else if (i>6 && i< 9){
    tdcomformance.appendChild(td)
  }else{
    tdsubstainablility.appendChild(td)
  }

  }
}



