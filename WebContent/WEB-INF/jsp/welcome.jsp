<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="MyApp">
<head>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
 <script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ng-grid/2.0.11/ng-grid.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ng-grid/2.0.11/ng-grid.css"></script> 
<link rel="stylesheet" href="css/ng-grid.min.css" />
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript">

var app = angular.module('MyApp', ['ngGrid']);
        app.controller('MyController', function ($scope, $http) {
            //This will hide the DIV by default.
        	
        	$scope.ask = function(){
        		$scope.IsHidden = $scope.IsHidden ? false : false;
        		$scope.IsHidden1 = true;
        		
        	};
        	
        	$scope.askQuestion = function(){
        		$scope.show = !$scope.show;
        		if(!$scope.show) {
        			$scope.IsHidden = true;
        		}
        		
        	};
        	
        	$scope.submitResponse = function() {
        		var reply = $('#response').val();
        		var threadId = $scope.currentTopic.id;
        		var responseObj = {response:reply,threadId:threadId};
        		$http.post("thread/saveResponse",JSON.stringify(responseObj)).success(function(response) {
        	         });
        		 window.location.reload(true);
        	};
        	
        	$scope.submitThread = function() {
        		var topic1 = $('#myselect').val();
        		var subject = $('#subject').val();
        		var description = $('#description').val();
        		
        		var j={};
        		j.topic=topic1;
        		j.subject=subject;
        		j.description=description;
        		
        		$http.post("thread/saveQuestion",JSON.stringify(j)).success(function(response) {
        			});
        		    
        		window.location.reload(true);
        	};
        	
        	
        	
        	
        	$scope.filterOptions = {
        	        filterText: "",
        	        useExternalFilter: true
        	    }; 
        	    $scope.totalServerItems = 0;
        	    $scope.pagingOptions = {
        	        pageSizes: [5, 10, 20],
        	        pageSize: 10,
        	        currentPage: 1
        	    };	
        	    $scope.setPagingData = function(data, page, pageSize){	
        	        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        	        $scope.topics1 = pagedData;
//        	        alert("$scope.topics " + $scope.topics1);
        	        $scope.totalServerItems = data.length;
        	        if (!$scope.$$phase) {
        	            $scope.$apply();
        	        }
        	    };
        	    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        	        setTimeout(function () {
        	            var data;
        	            if (searchText) {
        	            	var ft = searchText.toLowerCase();
        	            	data = $scope.topics.filter(function(item) {
        	            		return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
        	            	});
        	            	$scope.setPagingData(data,page,pageSize);
        	            } else {
        	                    $scope.setPagingData($scope.topics,page,pageSize);
        	            }
        	            $(".ngGroupPanel").removeClass("ng-hide");
        	            $(".ngTopPanel").css("height", "60px"); 
        	        }, 100);
        	    };
        		
        	    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        		
        	    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        	        if (newVal !== oldVal) {
        	          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        	        }
        	    }, true);
        	    $scope.$watch('filterOptions', function (newVal, oldVal) {
        	        if (newVal !== oldVal) {
        	          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        	        }
        	    }, true);
        	    
        	   $scope.mySelections = [];
        	   $scope.fetchResponse= function(topicObject){
        	   $scope.userResponse = [];
                   $http.get('http://localhost:8080/yashonline/ResponseList/'+topicObject.id).success(function (result){
                    $scope.userResponse=result;
                    $scope.response=result;
                    
                
                   
                   }); 
                    
                    
                  };
                  
                $scope.fetchResult = function() {
                	$http.get('http://localhost:8080/yashonline/list').success(function (data) {
                		
                		  $scope.topics1 = data;
                		  var startTime = Date.parseExact(data,'yyyy-MM-dd');
                		 });
                }
        	    $scope.gridOptions = {
        	    		
        	        data: 'topics1',
        	        enablePaging: true,
        			showFooter: true,
        	        totalServerItems: 'totalServerItems',
        	        multiSelect: false,
        	        selectedItems: $scope.mySelections,
        	        pagingOptions: $scope.pagingOptions,
        	        filterOptions: $scope.filterOptions,
        	        
        	        
//        	        headerRowTemplate:'<div class="ngRow ngHeaderText" ng-style="{height: headerRowHeight / 2}" style="text-align:center">I m other header row</div><div ng-style="{ height: col.headerRowHeight / 2, top: col.headerRowHeight / 2 }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngHeaderCell"><div class="ngVerticalBar" ng-style="{height: col.headerRowHeight / 2}" ng-class="{ ngVerticalBarVisible: !$last }">&nbsp;</div><div ng-header-cell></div></div>', 
        	        columnDefs: [{field: 'subject', displayName:'SUBJECT', cellTemplate: '<div class="ngCellText"><a href="#" ng-click="ShowHide(row.entity);fetchResponse(row.entity)">{{row.getProperty(col.field)}}</a></div>'},
        	                     {field:'topic',  displayName:'TOPIC'},
        	                     {field:'user.name', displayName:'POSTED BY'},
        	                     {field:'startTime', displayName:'DATE', cellTemplate: '<div class="ngCellText">{{row.entity.startTime | date:\'dd-MMM-yyyy\'}}</div>'},
        	                     
        	                     ]
        	    };
            $scope.searchTopic = "";
            $scope.IsHidden = true;
            $scope.currentTopic = {};
            $scope.currentPostedby = {};
            
            $scope.nameToShow = [];
            $scope.Show= [];
           
            $scope.changeInSearchTF = function() {
            	$('#tableData').paging({limit:5});
            };
            
            $scope.toShowReply = false;
            $scope.ShowHide = function (topicObject) {
            	//alert("topicObject " + JSON.stringify(topicObject));
            	$scope.toShowReply = false;
            	console.log('hi ' + JSON.stringify(topicObject));
            	$scope.currentTopic = topicObject;
            	
            	
            	$scope.nameToShow = topicObject.description;
            	
            	
            	
                //If DIV is hidden it will be visible and vice versa.
                $scope.IsHidden = $scope.IsHidden ? false : false;
                $scope.IsHidden1 = true;
              
            }
            
     
            $scope.reply = function() {
            	$scope.toShowReply = true;
            }
            
            $scope.hide = function () {
            	console.log('bye');
            	$scope.toShowReply = false;
                //If DIV is hidden it will be visible and vice versa.
                $scope.IsHidden = $scope.IsHidden ? false : true;
                $scope.IsHidden1 = false;
              
            }
            $scope.pageChangeHandler = function(num) {
                console.log('topic page changed to ' + num);
            };
            $scope.Show= function(){
            	
            	$scope.IsHidden= true;
            }
            
        });

    	$('#PageRefresh').click(function() {

        	      location.reload();

    	});

function OtherController($scope) {
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
}

app.controller('OtherController', OtherController);
</script>
<title>Demo</title>
<style type="text/css">.ngGrid{background-color:#fdfdfd}.ngGrid input[type="checkbox"]{margin:0;padding:0}.ngGrid input{vertical-align:top}.ngGrid.unselectable{-moz-user-select:none;-khtml-user-select:none;-webkit-user-select:none;-o-user-select:none;user-select:none}.ngViewport{overflow:auto;min-height:20px}.ngViewport:focus{outline:0}.ngCanvas{position:relative}.ngVerticalBar{position:absolute;right:0;width:0}.ngVerticalBarVisible{width:1px;background-color:#d4d4d4}.ngHeaderContainer{position:relative;overflow:hidden;font-weight:bold;background-color:inherit}.ngHeaderCell{position:absolute;top:0;bottom:0;background-color:inherit}.ngHeaderCell.pinned{z-index:1}.ngHeaderSortColumn{position:absolute;overflow:hidden}.ngTopPanel{position:relative;z-index:1;background-color:#eaeaea;border-bottom:1px solid #d4d4d4}.ngSortButtonDown{position:absolute;top:3px;left:0;right:0;margin-left:auto;margin-right:auto;border-color:gray transparent;border-style:solid;border-width:0 5px 5px 5px;height:0;width:0}.ngNoSort{cursor:default}.ngHeaderButton{position:absolute;right:2px;top:8px;-moz-border-radius:50%;-webkit-border-radius:50%;border-radius:50%;width:14px;height:14px;z-index:1;background-color:#9fbbb4;cursor:pointer}.ngSortButtonUp{position:absolute;top:3px;left:0;right:0;margin-left:auto;margin-right:auto;border-color:gray transparent;border-style:solid;border-width:5px 5px 0 5px;height:0;width:0}.ngHeaderScroller{position:absolute;background-color:inherit}.ngSortPriority{position:absolute;top:-5px;left:1px;font-size:6pt;font-weight:bold}.ngHeaderGrip{cursor:col-resize;width:10px;right:-5px;top:0;height:100%;position:absolute;background-color:transparent}.ngHeaderText{padding:5px;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box;white-space:nowrap;-ms-text-overflow:ellipsis;-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden}.ngHeaderButtonArrow{position:absolute;top:4px;left:3px;width:0;height:0;border-style:solid;border-width:6.5px 4.5px 0 4.5px;border-color:#4d4d4d transparent transparent transparent}.ngPinnedIcon{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAOwgAADsIBFShKgAAAABp0RVh0U29mdHdhcmUAUGFpbnQuTkVUIHYzLjUuMTAw9HKhAAAAmElEQVQoU33PQapBURjA8UtkwJuaWYGSgfQWYBMvczPmTCzAAGVuaA228BZhRCkDGSmE31FucuRfvzq3vr5zT/JSjSU7DsypEPXDkDVn2hSIytJhw4kWGaLCxgHh2gt/RBuLzNhz5caWPjnSqqw4EraFfwznf8qklWjwy4IRTerkiQoPGtPl40OehcEJvcfXl8LglLfBJLkDcMgbgHlHhK8AAAAASUVORK5CYII=);background-repeat:no-repeat;position:absolute;right:5px;top:5px;height:10px;width:10px}.ngUnPinnedIcon{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAOwgAADsIBFShKgAAAABp0RVh0U29mdHdhcmUAUGFpbnQuTkVUIHYzLjUuMTAw9HKhAAAAlElEQVQoU33PPQrCQBRF4fFnI2KfZVi5ARvdgo1l6mwmkCJVOgluwd5OwUoDtnoOxAei8cLXTN7cvEl/skCNDCMPfsUPO5zQwOHIDEvYtMURHe6wOVLgigvOePRyeDkyR4ln7wZ//7XfFBu8B23+aDJjrHGAwza7hjtHJvDmHg7b7Bru7AMjK7Rw2ObBVHDY5oGk9AKQNB2zy8MBTgAAAABJRU5ErkJggg==);background-repeat:no-repeat;position:absolute;height:10px;width:10px;right:5px;top:5px}.ngColMenu{right:2px;padding:5px;top:25px;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;background-color:#bdd0cb;position:absolute;border:2px solid #d4d4d4;z-index:1}.ngColListCheckbox{position:relative;right:3px;top:4px}.ngColList{list-style-type:none}.ngColListItem{position:relative;right:17px;top:2px;white-space:nowrap}.ngMenuText{position:relative;top:2px;left:2px}.ngGroupPanel{background-color:#eaeaea;overflow:hidden;border-bottom:1px solid #d4d4d4}.ngGroupPanelDescription{margin-top:5px;margin-left:5px}.ngGroupList{list-style-type:none;margin:0;padding:0}.ngAggHeader{position:absolute;border:0}.ngGroupElement{float:left;height:100%;width:100%}.ngGroupIcon{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAANCAYAAACZ3F9/AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAEFJREFUKFNjoAhISkr+h2J5JDZODNXGwGBsbPwfhIGAA8bGh6HaGBiAGhxAGJmND4M1gQCSM0adCsVQbcPcqQwMALWDGyDvWPefAAAAAElFTkSuQmCC);background-repeat:no-repeat;height:15px;width:15px;position:absolute;right:-2px;top:2px}.ngGroupedByIcon{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAANCAYAAACZ3F9/AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAElJREFUKFNjoAhISkr+R8LyaHwMDNXGwGBsbPwfhoGAA5mPDUO1oWpE52PDYE0gALTFAYbR+dgwWBMIoPlh1I9ADNU2NPzIwAAAFQYI9E4OLvEAAAAASUVORK5CYII=);background-repeat:no-repeat;height:15px;width:15px;position:absolute;right:-2px;top:2px}.ngGroupName{background-color:#fdfdfd;border:1px solid #d4d4d4;padding:3px 10px;float:left;margin-left:0;margin-top:2px;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;font-weight:bold}.ngGroupArrow{width:0;height:0;border-top:6px solid transparent;border-bottom:6px solid transparent;border-left:6px solid black;margin-top:10px;margin-left:5px;margin-right:5px;float:right}.ngGroupingNumber{position:absolute;right:-10px;top:-2px}.ngAggArrowCollapsed{position:absolute;left:8px;bottom:10px;width:0;height:0;border-style:solid;border-width:5px 0 5px 8.7px;border-color:transparent transparent transparent #000}.ngGroupItem{float:left}.ngGroupItem:first-child{margin-left:2px}.ngRemoveGroup{width:5px;-moz-opacity:.4;opacity:.4;margin-top:-1px;margin-left:5px}.ngRemoveGroup:hover{color:black;text-decoration:none;cursor:pointer;-moz-opacity:.7;opacity:.7}.ngAggArrowExpanded{position:absolute;left:8px;bottom:10px;width:0;height:0;border-style:solid;border-width:0 0 9px 9px;border-color:transparent transparent #000 transparent}.ngAggregate{position:absolute;background-color:#c9dde1;border-bottom:1px solid beige;overflow:hidden;top:0;bottom:0;right:-1px;left:0}.ngAggregateText{position:absolute;left:27px;top:5px;line-height:20px;white-space:nowrap}.ngRow{position:absolute;border-bottom:1px solid #d4d4d4}.ngRow.odd{background-color:#fdfdfd}.ngRow.even{background-color:#f3f3f3}.ngRow.selected{background-color:#c9dde1}.ngCell{overflow:hidden;position:absolute;top:0;bottom:0;background-color:inherit}.ngCell.pinned{z-index:1}.ngCellText{padding:5px;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box;white-space:nowrap;-ms-text-overflow:ellipsis;-o-text-overflow:ellipsis;text-overflow:ellipsis;overflow:hidden}.ngSelectionCell{margin-top:9px;margin-left:6px}.ngSelectionHeader{position:absolute;top:11px;left:6px}.ngCellElement:focus{outline:0;background-color:#b3c4c7}.ngRow.canSelect{cursor:pointer}.ngSelectionCheckbox{margin-top:9px;margin-left:6px}.ngFooterPanel{background-color:#eaeaea;padding:0;border-top:1px solid #d4d4d4;position:relative}.nglabel{display:block;float:left;font-weight:bold;padding-right:5px}.ngTotalSelectContainer{float:left;margin:5px;margin-top:7px}.ngFooterSelectedItems{padding:2px}.ngFooterTotalItems.ngnoMultiSelect{padding:0!important}.ngPagerFirstBar{width:10px;border-left:2px solid #4d4d4d;margin-top:-6px;height:12px;margin-left:-3px}.ngPagerButton{height:25px;min-width:26px}.ngPagerFirstTriangle{width:0;height:0;border-style:solid;border-width:5px 8.7px 5px 0;border-color:transparent #4d4d4d transparent transparent;margin-left:2px}.ngPagerNextTriangle{margin-left:1px}.ngPagerPrevTriangle{margin-left:0}.ngPagerLastTriangle{width:0;height:0;border-style:solid;border-width:5px 0 5px 8.7px;border-color:transparent transparent transparent #4d4d4d;margin-left:-1px}.ngPagerLastBar{width:10px;border-left:2px solid #4d4d4d;margin-top:-6px;height:12px;margin-left:1px}.ngFooterTotalItems{padding:2px}</style>

<style>
.footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:9px;	 
   font-size:11px;	 
}
div.scroll {
    background-color: #00FFFF;
    width: 200px;
    height: 50px;
    overflow: scroll;
    font-size:20px;

}

.paging-nav {
  text-align: right;
  padding-top: 2px;
 
}

.paging-nav a {
  margin: auto 1px;
  text-decoration: none;
  display: inline-block;
  padding: 1px 7px;
  background: #91b9e6;
  color: white;
  border-radius: 3px;
  margin-top:-15px;
  
   
}

.gridStyle {
    border: 1px solid rgb(212,212,212);
    width: 600px; 
    height: 300px;
}

.paging-nav .selected-page {
  background: #187ed5;
  font-weight: bold;
}

.paging-nav,
#tableData,tr,td{/* 
border:2px solid black; */
padding:20px;
  width: 250px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
  margin-right: 130px;
  margin-top: -15px;
 
  
  
}
.container{
boreder:2px solid black;
width:100%;

}
.header{
width:100%;
height:70px;
border:2px solid black;
background-color:#7D1935;
text-align:center;

}
.main{
width:100%;
height:510px;
border:2px solid black;

margin-top:5px;

}

#menuh-container
	{
	position: absolute;		
	top: 1em;
	left: 1em;

	}

#menuh
	{
	font-size: small;
	font-family: arial, helvetica, sans-serif;
	width:200%;
	float:left;
	margin:2em;
	margin-top: 1em;
	
	}
		
#menuh a
	{
	text-align: center;
	display:block;
	border: 1px solid #0040FF;
	white-space:nowrap;
	margin:0;
	padding:15px;
	}
	
#menuh a:link, #menuh a:visited, #menuh a:active	
	{
	color: white;
	background-color:#696969 ;		
	text-decoration:none;
	width:200px;
	padding:20px;
	}
	
#menuh a:hover					
	{
	
	color: white;
	background-color: #000000;	
	text-decoration:none;
	}	
	
#menuh a.top_parent, #menuh a.top_parent:hover  
	{
	background-image: url(navdown_white.gif);
	background-position: right center;
	background-repeat: no-repeat;
	}
	
#menuh a.parent, #menuh a.parent:hover 	
	{
	background-image: url(nav_white.gif);
	background-position: right center;
	background-repeat: no-repeat;
	}

#menuh ul
	{
	list-style:none;
	margin:0;
	padding:0;
	float:left;
	width:50em;	
	}

#menuh li
	{
	position:relative;
	min-height: 5px;		
	vertical-align: bottom;		
	}

#menuh ul ul
	{
	position:absolute;
	z-index:500;
	top:auto;
	display:none;
	/* padding: 1em; */
	margin:0em 0 0 0em;
	}

#menuh ul ul ul
	{
	
	top:0;
	left:100%;
	}

div#menuh li:hover
	{
	cursor:pointer;
	
	z-index:100;
	}

div#menuh li:hover ul ul,
div#menuh li li:hover ul ul,
div#menuh li li li:hover ul ul,
div#menuh li li li li:hover ul ul
{display:none;}

div#menuh li:hover ul,
div#menuh li li:hover ul,
div#menuh li li li:hover ul,
div#menuh li li li li:hover ul
{display:block;}


img {
    max-width: 100%;
    max-height: 100%;
}
#panelGuest{
    
    overflow:scroll;
    height: 400px; 
    width: 1100px;
    max-width:820px;
    padding: 30px";
    color:#7D1935;
}
</style>
</head>
<body style="background-color:#FDF3E7;" ng-app="MyApp" ng-controller="MyController" ng-init="fetchResult()">

 <form:form action="thread/saveQuestion"  modelAttribute="thread"  method="post"> 


<div class="container">
<div class="header">
<div style="width:90px;height:55px;float:left;" ><img src="/yashonline/images/Yash.jpeg"/></div>
<div style="   margin-left:10px; float:right;"><a href=""><font color="white">Contact Us</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <a href="<c:url value='/logout'/>"><font color="white"><b>Logout</b></font></a>
 </div>
<div>
 <p><center><h3><font color="white"><b> WELCOME TO YASH ONLINE  </h3></center>  </b></font> </p></div>
 
<div class="main">

 <font color="red" ><marquee   direction="left" style="background:#3B3738;height:45px;"><b>WELCOME TO YASH TECHNOLOGIES PVT. LTD. </b> </marquee></font>
   
<!--      <input id="search" ng-model = "searchTopic" type="text" placeholder="Type here" style="margin-left:600px;margin-top:5px;" size="25" >
     <input id="submit" type="submit" value="Search"> -->
     <!-- <div class="col-xs-2"  style="margin-left:1100px;margin-top:0px;" size="5">
            <input ng-model="filterOptions.filterText" id="search" class="form-control" placeholder="Search Topic"></br>
             </div> -->
             
           
        
                 <!-- this is second page div         -->
                 
  <div ng-show = "!IsHidden && !show" ng-app="" style="margin-top:0 px;border:10px;text-align:center;height:200px;width: 799px;margin-left: 200px; ">
<div data-role="header" style="background: #A9A9A9 ;margin-left: -30px; height:60px;margin-top:-20px;" >
     <h1 ><font color="red" size="6">Posted By : {{currentTopic.user.name}}  !!!!!!!!!!!           Date : {{currentTopic.startTime | date : format : timezone}}</font></h1> 	
       <a href="#" ng-click="hide()">back</a>          
    <a href="#" ng-click = "collapsed=!collapsed || reply()" ng-model="collapsed" style="margin-left:600px;margin-top:0px;">Replay</a>
    
    
    
  </div> 
  <div class="panel panel-default" style="postion:relative;">
            <div class="panel-body" id=panelGuest style="border: thin solid gray">  
             <h4><font color="red">{{currentTopic.description}}</font></h4> 
             
             <span  ng-repeat="response in userResponse" >
            <span > <font color="black" size="4">{{response.response}}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{response.responseTimestamp | date : format : timezone}}&emsp;&emsp;{{response.user.name}}`</font></span></br></br></span><br/>
                       
  
    </div>
    </div>
    <form  action="/thread/saveResponse"  modelAttribute="response"  method="post" >
    <div ng-show="toShowReply" style="position:absolute;top:435px;left:230px"  >
	<textarea ng-show="collapsed"  id="response" name="response" rows="5" cols="100"  style="background-color::#80BFFF"; margin-top:-20px;" ></textarea>
	
<input type="button" ng-show="collapsed" ng-click="submitResponse()"  value="Save"/> 
 	 </div> 
   </form>

</div>
    <div id="menuh-container"  >
    <div id="menuh"   >
	<ul style="margin-top:80px;width:200px; height:35px;margin-left:-22px;"  >
		<li><a href="#" class="top_parent"><b>Category</b></a>
        	<ul style="background-color:#87CEEB;width: 200px;height: 400px;overflow: scroll;display:block; " >
			
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo2</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo3</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo4</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo4</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo5</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo6</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			<li><a href="#" class="parent" ng-click="ShowHide()">Demo1</a></li>
			
		</ul>
		</li>
	</ul>
	</div>
</div>   
<br>
<br>
 <div ng-show="IsHidden && !show" class="gridStyle" ng-grid="gridOptions" style="margin-left:400px;margin-top:40px;" ng-model="table" ></div>  
<%-- <form action="addDetails"  modelAttribute="Thread"  method="post"> --%>
 <a href="#" ng-click="askQuestion()"  style="margin-left:1100px;margin-top:0px;" ng-model="ask">ASK A Question</a>

    <div ng-show="show"  style="margin-top:-20px;"  >
   
   <h1>Topic/Categary</h1>
    <h4>Topics:</h4>
     <select id="myselect"  value="" ng-options="topic.topic for topic in topics1"  name="topic"  >
         <option value="dropdown">Pls select one
        <option value="java">JAVA
        <option value=".Net">.NET
        <option value="anroid">ANROID
         <option value="software">SOFTWARE
          <option value="hardware">HARDWARE
    </select>
     
       
       <h4> Title Of Question: </h4>
  <input type="text" name="subject"  id="subject" value="" style="width:400px;height:25px;"/> 
 
      <h4>More Details:</h4>
<textarea  name="description"  id="description" value="" rows="5" cols="50"></textarea>
       <br>
       <!-- <input type="submit" value="save"/> --><input type="button" ng-click="submitThread()"  id="PageRefresh"  value="Save" /> <br>
       
       <input type="file" name="fileUpload" size="50" style="float: right;margin-right:-50px;" />
     <!--   <input type="submit" value="Upload" /> -->
     
        <a href="<c:url value='/upload' />">File-Attachment</a> 
   </div> 
  <!--  <script type="text/javascript">
	
	$('#PageRefresh').click(function() {

    	      location.reload();

	});

</script -->>
            
<script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:5});
               
            });
        </script>
        <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</div>

 <%--   <a href="<c:url value='/ResponseList' />">list</a> --%>
           

<div class="footer">
Copyright © 2015. YASH Technologies. All Rights Reserved </div>
</div>

   </form:form>
 <!--   <meta http-equiv="refresh" content="5" /> -->

</body>
</html>