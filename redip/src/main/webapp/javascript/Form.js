function selectAll(selectBox, selectAll) {
	// have we been passed an ID
	if (typeof selectBox == "string") {
		selectBox = document.getElementsByName("Country");
	}

	// is the select box a multiple select box?
	if (selectBox.type == "select-multiple") {
		for ( var i = 0; i < selectBox.options.length; i++) {
			selectBox.options[i].selected = selectAll;
		}
	} else {
		for ( var i = 0; i < selectBox.length; i++) {
			selectBox[i].checked = selectAll;
		}
	}
}

function popup(mylink) {
	window.open(mylink, 'popup',
			'width=1000,height=400,scrollbars=yes,menubar=false,location=false');
}


function popupFrame(mylink) {
        window.open(mylink, 'thepop', 'width=400,height=300,top=200,left=200,directories=no,location=no,menubar=no,scrollbars=no,status=no,toolbar=no,resizable=yes');
}

function enableField(element) {
	document.getElementById(element).disabled = false;
}

function disableField(element) {
	document.getElementById(element).disabled = true;
}

function EnvTuning(arg) {
	/*
	 * If not Production URL, set html background color different
	 */
	if ((arg.toString().indexOf("PROD", 0) == -1)  &&
            (arg.toString().indexOf("PREPROD", 0) == -1) && 
            (arg.toString().indexOf("prd", 0) == -1)){
		document.body.style.background = "#FFFFCC";
	}
}


function menuColoring(arg) {
	/*
	 * Put actual page button more visible
	 */
	var menuCollection = document.getElementsByName('menu');

        var unicityMenu = 0;
	for ( var cpt_menu = menuCollection.length - 1 ; cpt_menu >= 0; cpt_menu--) {
                
		if (location.toString().indexOf(menuCollection[cpt_menu]) != -1) {
                    if (unicityMenu == 0){
			menuCollection[cpt_menu].style.background = "#cad3f1";
                        menuCollection[cpt_menu].parentNode.parentNode.parentNode.firstChild.style.background = "#cad3f1";
                    unicityMenu = 1;
                }
                    
            }
           
	}
	
        if (location.toString().indexOf("jsp") == -1) {
			document.getElementById("Index").style.background = "#cad3f1";
                    }
}

/*
 * Gestion des clefs, Mandatory, KEY
 */
function keyOnFocus(arg) {
	if (arg.value == 'Mandatory, KEY') {
		arg.value = '';
		arg.style.color = '#000000';
		arg.style.fontStyle = 'normal';
	}
}

function keyOnBlur(arg) {
	if (arg.value == '') {
		arg.value = 'Mandatory, KEY';
		arg.style.color = '#FF0000';
		arg.style.fontStyle = 'italic';
	}
}

function resetReportFilter() {
	document.getElementById('build').value = 'All';
	document.getElementById('revision').value = 'All';
	document.getElementById('env').value = 'All';
	document.getElementById('app').value = 'All';
	document.getElementById('ip').value = 'All';

	document.getElementById('ReportFilters').submit();
}

/*
 * Functions used for dynamic tables
 */
function addNCImpact(ncid) {
	TR = document.createElement('tr');
        
        
	/* Country */
//        var form0 = document.createElement('input');
//	form0.setAttribute('name', 'Country_');
//	form0.setAttribute('style', 'width:123px');
//        form0.setAttribute('class', 'wob'); 
        var TD0 = document.createElement('td');
        TD0.setAttribute('class', 'simpleline');
        TD0.setAttribute('style', 'background-color:white');
//	TD0.appendChild(form0);
	TR.appendChild(TD0);
        
        /* Application */
        var form1 = document.createElement('input');
	form1.setAttribute('name', 'Application_');
        form1.setAttribute('id', 'Application_');
	form1.setAttribute('style', 'width:250px');
        form1.setAttribute('class', 'wob'); 
        var TD1 = document.createElement('td');
        TD1.setAttribute('class', 'simpleline');
        TD1.setAttribute('style', 'background-color:white');
	TD1.appendChild(form1);
	TR.appendChild(TD1);
        
        /* StartDate */
        var form2 = document.createElement('input');
	form2.setAttribute('name', 'StartDate_');
	form2.setAttribute('style', 'width:123px');
        form2.setAttribute('class', 'wob'); 
        var TD2 = document.createElement('td');
        TD2.setAttribute('class', 'simpleline');
        TD2.setAttribute('style', 'background-color:white');
	TD2.appendChild(form2);
	TR.appendChild(TD2);
        
        /* StartTime */
        var form3 = document.createElement('input');
	form3.setAttribute('name', 'StartTime_');
	form3.setAttribute('style', 'width:123px');
        form3.setAttribute('class', 'wob'); 
        var TD3 = document.createElement('td');
        TD3.setAttribute('class', 'simpleline');
        TD3.setAttribute('style', 'background-color:white');
	TD3.appendChild(form3);
	TR.appendChild(TD3);
        
        /* EndDate */
        var form4 = document.createElement('input');
	form4.setAttribute('name', 'EndDate_');
	form4.setAttribute('style', 'width:123px');
        form4.setAttribute('class', 'wob'); 
        var TD4 = document.createElement('td');
        TD4.setAttribute('class', 'simpleline');
        TD4.setAttribute('style', 'background-color:white');
	TD4.appendChild(form4);
	TR.appendChild(TD4);
        
        /* EndTime */
        var form5 = document.createElement('input');
	form5.setAttribute('name', 'EndTime_');
	form5.setAttribute('style', 'width:123px');
        form5.setAttribute('class', 'wob'); 
        var TD5 = document.createElement('td');
        TD5.setAttribute('class', 'simpleline');
        TD5.setAttribute('style', 'background-color:white');
	TD5.appendChild(form5);
	TR.appendChild(TD5);
        
        /* ImpactOrCost */
        var form6 = document.createElement('input');
	form6.setAttribute('name', 'ImpactOrCost_');
	form6.setAttribute('style', 'width:123px');
        form6.setAttribute('class', 'wob'); 
        var TD6 = document.createElement('td');
        TD6.setAttribute('class', 'simpleline');
        TD6.setAttribute('style', 'background-color:white');
	TD6.appendChild(form6);
	TR.appendChild(TD6);
        /* ID */
        var form7 = document.createElement('input');
	form7.setAttribute('name', 'IdLog_');
	form7.setAttribute('style', 'display:none');
        form7.setAttribute('value', ncid); 
        var TD7 = document.createElement('td');
        TD7.setAttribute('class', 'wob');
        TD7.appendChild(form7);
	TR.appendChild(TD7);
        
        document.getElementById('nonconfimpact').appendChild(TR);
        document.getElementById('saveImpactButton').style.display = "inline";
}


function setInvisibleDiv(element) {
	document.getElementById(element).style.display = "none";
	}
        
function setVisibleDiv(element) {
	document.getElementById(element).style.display = "inline";
	}

/*
 * Functions used for dynamic tables
 */
function addNCAction(ncid) {
	TR = document.createElement('tr');
        
        
	
        /* Action */
        var form1 = document.createElement('input');
	form1.setAttribute('name', 'Action_Action');
	form1.setAttribute('style', 'width:680px');
        form1.setAttribute('class', 'wob'); 
        var TD1 = document.createElement('td');
        TD1.setAttribute('class', 'simpleline');
        TD1.setAttribute('style', 'background-color:white');
	TD1.appendChild(form1);
	TR.appendChild(TD1);
        
        /* Date */
        var form2 = document.createElement('input');
	form2.setAttribute('name', 'Action_Date');
	form2.setAttribute('style', 'width:100px');
        form2.setAttribute('class', 'wob'); 
        var TD2 = document.createElement('td');
        TD2.setAttribute('class', 'simpleline');
        TD2.setAttribute('style', 'background-color:white');
	TD2.appendChild(form2);
	TR.appendChild(TD2);
        
        /* Deadline */
        var form3 = document.createElement('input');
	form3.setAttribute('name', 'Action_Deadline');
	form3.setAttribute('style', 'width:100px');
        form3.setAttribute('class', 'wob'); 
        var TD3 = document.createElement('td');
        TD3.setAttribute('class', 'simpleline');
        TD3.setAttribute('style', 'background-color:white');
	TD3.appendChild(form3);
	TR.appendChild(TD3);
        
        /* Follower */
        var form4 = document.createElement('input');
	form4.setAttribute('name', 'Action_Follower');
	form4.setAttribute('style', 'width:100px');
        form4.setAttribute('class', 'wob'); 
        var TD4 = document.createElement('td');
        TD4.setAttribute('class', 'simpleline');
        TD4.setAttribute('style', 'background-color:white');
	TD4.appendChild(form4);
	TR.appendChild(TD4);
        
        /* Percentage */
        var form5 = document.createElement('input');
	form5.setAttribute('name', 'Action_Percentage');
	form5.setAttribute('style', 'width:100px');
        form5.setAttribute('class', 'wob'); 
        var TD5 = document.createElement('td');
        TD5.setAttribute('class', 'simpleline');
        TD5.setAttribute('style', 'background-color:white');
	TD5.appendChild(form5);
	TR.appendChild(TD5);
        
        /* Status */
        var form6 = document.createElement('input');
	form6.setAttribute('name', 'Action_Status');
	form6.setAttribute('style', 'width:100px');
        form6.setAttribute('class', 'wob'); 
        var TD6 = document.createElement('td');
        TD6.setAttribute('class', 'simpleline');
        TD6.setAttribute('style', 'background-color:white');
	TD6.appendChild(form6);
	TR.appendChild(TD6);
        /* ID */
        var form7 = document.createElement('input');
	form7.setAttribute('name', 'Action_ID');
	form7.setAttribute('style', 'display:none');
        form7.setAttribute('value', ncid); 
        var TD7 = document.createElement('td');
        TD7.setAttribute('class', 'wob');
        TD7.appendChild(form7);
	TR.appendChild(TD7);
        
        document.getElementById('nonconfaction').appendChild(TR);
        document.getElementById('saveActionButton').style.display = "inline";
}

function addNCExchange(ncid) {
	TR = document.createElement('tr');
        
        /* Date */
        var form1 = document.createElement('input');
	form1.setAttribute('name', 'Exchange_Date');
	form1.setAttribute('style', 'width:100px');
        form1.setAttribute('class', 'wob'); 
        var TD1 = document.createElement('td');
        TD1.setAttribute('class', 'simpleline');
        TD1.setAttribute('style', 'background-color:white');
	TD1.appendChild(form1);
	TR.appendChild(TD1);
        
        /* Title */
        var form2 = document.createElement('input');
	form2.setAttribute('name', 'Exchange_Title');
	form2.setAttribute('style', 'width:100px');
        form2.setAttribute('class', 'wob'); 
        var TD2 = document.createElement('td');
        TD2.setAttribute('class', 'simpleline');
        TD2.setAttribute('style', 'background-color:white');
	TD2.appendChild(form2);
	TR.appendChild(TD2);
        
        /* Deadline */
        var form3 = document.createElement('input');
	form3.setAttribute('name', 'Exchange_User');
	form3.setAttribute('style', 'width:100px');
        form3.setAttribute('class', 'wob'); 
        var TD3 = document.createElement('td');
        TD3.setAttribute('class', 'simpleline');
        TD3.setAttribute('style', 'background-color:white');
	TD3.appendChild(form3);
	TR.appendChild(TD3);
        
        /* Follower */
        var form4 = document.createElement('input');
	form4.setAttribute('name', 'Exchange_Exchange');
	form4.setAttribute('style', 'width:880px');
        form4.setAttribute('class', 'wob'); 
        var TD4 = document.createElement('td');
        TD4.setAttribute('class', 'simpleline');
        TD4.setAttribute('style', 'background-color:white');
	TD4.appendChild(form4);
	TR.appendChild(TD4);
        
        
        
        document.getElementById('nonconfexchange').appendChild(TR);
        document.getElementById('saveExchangeButton').style.display = "inline";
}
