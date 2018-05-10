var app = angular.module('MyApp', ['ngGrid']);
        app.controller('MyController', function ($scope) {
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
        	    
        	    $scope.topics = [{topic:'JAVA', postedby:'Rahul', date:'02/11/2015', names:[{name:'Java is a programming language expressly designed for use in the distributed environment of the Internet. It was designed to have the "look and feel" of the C++ language, but it is simpler to use than C++ and enforces an object-oriented programming model. Java can be used to create complete applications that may run on a single computer or be distributed among servers and clients in a network. It can also be used to build a small application module or applet for use as part of a Web page. Applets make it possible for a Web page user to interact with the page.', country:'James Gosling'},{name:' Object-Oriented: Unlike C++ which is semi object-oriented, Java is a fully object-oriented programming language. It has all OOP features such as abstraction, encapsulation, inheritance and polymorphism.	Oracle Corporation', country:' '},{name:'A platform is the hardware or software environment in which a program runs. There are two types of platforms software-based and hardware-based. Java provides software-based platform. The Java platform differs from most other platforms in the sense that its a software-based platform that runs on top of other hardware-based platforms.It has two components: ', country:' James Gosling '}]},
                                 {topic:'Spring', postedby:'Rahul1', date:'03/10/2015', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'PHP', postedby:'Rahul1', date:'04/8/2014', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'ANROID', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C++', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'ANROID', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C++', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'JAVA', postedby:'Rahul', date:'12:30', names:[{name:'Rahul', country:'India'},{name:'123', country:'Bang'},{name:'456', country:'Pak'}]},
                                 {topic:'Spring', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'PHP', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'ANROID', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C++', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'ANROID', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]},
                                 {topic:'C++', postedby:'Rahul1', date:'13:30', names:[{name:'2', country:'2'},{name:'3', country:'3'},{name:'4', country:'4'}]}];
                $scope.topics1 = angular.copy($scope.topics);
                $scope.mySelections = [];
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
        	        columnDefs: [{field: 'topic', displayName:'Topic', cellTemplate: '<div class="ngCellText"><a href="#" ng-click="ShowHide(row.entity)">{{row.getProperty(col.field)}}</a></div>'},
        	                     {field:'postedby', displayName:'Posted By:'},
        	                     {field:'date', displayName:'Date'},
        	                     {field:'names', displayName:'Name', visible : false}]
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
//            	alert("topicObject " + JSON.stringify(topicObject));
            	$scope.toShowReply = false;
            	console.log('hi ' + JSON.stringify(topicObject));
            	$scope.currentTopic = topicObject;
            	
            	
            	$scope.nameToShow = topicObject.names;
            	
            	
            	
                //If DIV is hidden it will be visible and vice versa.
                $scope.IsHidden = $scope.IsHidden ? false : false;
                $scope.IsHidden1 = true;
              
            }
            
       /*    $scope.ShowHide = function (postedbyObject) {
            	$scope.toShowReply = false;
            	console.log('hi');
            	$scope.currentPostedby = postedbyObject;
            	
            	
            	$scope.nameToShow = postedbyObject.names;
            	
            	
            	
                //If DIV is hidden it will be visible and vice versa.
                $scope.IsHidden = $scope.IsHidden ? false : false;
                $scope.IsHidden1 = true;
              
            }*/
            
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


function OtherController($scope) {
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
}

app.controller('OtherController', OtherController);