/**
 * 	Generated by mxmlc 2.0
 *
 *	Package:	Components
 *	Class: 		DAGNode
 *	Source: 	C:\Eclipse\workspace\catissuecore_Merge\flexclient\dag\Components\DAGNode.mxml
 *	Template: 	flex2/compiler/mxml/gen/ClassDef.vm
 *	Time: 		2007.09.21 11:53:38 GMT+05:30
 */

package Components
{

import flash.accessibility.*;
import flash.debugger.*;
import flash.display.*;
import flash.errors.*;
import flash.events.*;
import flash.events.MouseEvent;
import flash.external.*;
import flash.filters.*;
import flash.geom.*;
import flash.media.*;
import flash.net.*;
import flash.printing.*;
import flash.profiler.*;
import flash.system.*;
import flash.text.*;
import flash.ui.*;
import flash.utils.*;
import flash.utils.IExternalizable;
import flash.xml.*;
import mx.binding.*;
import mx.containers.Box;
import mx.containers.Canvas;
import mx.containers.HBox;
import mx.containers.VBox;
import mx.controls.Button;
import mx.controls.ComboBox;
import mx.controls.Label;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.Repeater;
import mx.core.UIComponentDescriptor;
import mx.core.mx_internal;
import mx.events.FlexEvent;
import mx.events.ListEvent;
import mx.styles.*;



public class DAGNode
	extends mx.containers.Box
	implements flash.utils.IExternalizable
{

	[Bindable]
/**
 * @private
 **/
	public var _HBox1 : mx.containers.HBox;

	[Bindable]
/**
 * @private
 **/
	public var _Label1 : mx.controls.Label;

	[Bindable]
/**
 * @private
 **/
	public var _Label2 : mx.controls.Label;

	[Bindable]
/**
 * @private
 **/
	public var _Label3 : Array;

	[Bindable]
/**
 * @private
 **/
	public var _VBox1 : mx.containers.VBox;

	[Bindable]
/**
 * @private
 **/
	public var assRep : mx.core.Repeater;

	[Bindable]
/**
 * @private
 **/
	public var assVbox : mx.containers.VBox;

	[Bindable]
/**
 * @private
 **/
	public var operatorCombo : Array;




private var _documentDescriptor_ : mx.core.UIComponentDescriptor = 
new mx.core.UIComponentDescriptor({
  type: mx.containers.Box
  ,
  propertiesFactory: function():Object { return {
    childDescriptors: [
      new mx.core.UIComponentDescriptor({
        type: mx.containers.HBox
        ,
        id: "_HBox1"
        ,
        stylesFactory: function():void {
          this.borderStyle = "solid";
          this.paddingBottom = 5;
          this.paddingLeft = 5;
          this.paddingRight = 5;
          this.paddingTop = 5;
        }
        ,
        propertiesFactory: function():Object { return {
          name: "Node",
          percentWidth: 100.0,
          childDescriptors: [
            new mx.core.UIComponentDescriptor({
              type: mx.containers.Canvas
              ,
              stylesFactory: function():void {
                this.backgroundColor = 16119285;
                this.borderStyle = "solid";
              }
              ,
              propertiesFactory: function():Object { return {
                childDescriptors: [
                  new mx.core.UIComponentDescriptor({
                    type: mx.controls.Label
                    ,
                    id: "_Label1"
                  })
                ]
              }}
            })
          ,
            new mx.core.UIComponentDescriptor({
              type: mx.controls.Label
              ,
              id: "_Label2"
              ,
              stylesFactory: function():void {
                this.color = 255;
              }
              ,
              propertiesFactory: function():Object { return {
                styleName: "Arial"
              }}
            })
          ,
            new mx.core.UIComponentDescriptor({
              type: mx.controls.Button
              ,
              events: {
                click: "___Button1_click"
              }
              ,
              propertiesFactory: function():Object { return {
                label: ">"
              }}
            })
          ]
        }}
      })
    ,
      new mx.core.UIComponentDescriptor({
        type: mx.containers.VBox
        ,
        id: "assVbox"
        ,
        stylesFactory: function():void {
          this.verticalGap = 0;
        }
        ,
        propertiesFactory: function():Object { return {
          name: "Association",
          label: "Associations",
          percentWidth: 100.0,
          visible: false,
          childDescriptors: [
            new mx.core.UIComponentDescriptor({
              type: mx.core.Repeater
              ,
              id: "assRep"
              ,
              propertiesFactory: function():Object { return {
                name: "Repeater",
                childDescriptors: [
                  new mx.core.UIComponentDescriptor({
                    type: mx.containers.HBox
                    ,
                    stylesFactory: function():void {
                      this.borderStyle = "solid";
                    }
                    ,
                    propertiesFactory: function():Object { return {
                      name: "hbox",
                      percentWidth: 100.0,
                      childDescriptors: [
                        new mx.core.UIComponentDescriptor({
                          type: mx.containers.Canvas
                          ,
                          stylesFactory: function():void {
                            this.backgroundColor = 16119285;
                            this.borderStyle = "solid";
                          }
                          ,
                          propertiesFactory: function():Object { return {
                            childDescriptors: [
                              new mx.core.UIComponentDescriptor({
                                type: mx.controls.Label
                                ,
                                id: "_Label3"
                              })
                            ]
                          }}
                        })
                      ,
                        new mx.core.UIComponentDescriptor({
                          type: mx.controls.ComboBox
                          ,
                          id: "operatorCombo"
                          ,
                          events: {
                            change: "__operatorCombo_change"
                          }
                        })
                      ]
                    }}
                  })
                ]
              }}
            })
          ]
        }}
      })
    ]
  }}
})

    /**
     * @private
     **/
	public function DAGNode()
	{
		super();

		mx_internal::_document = this;

		//	our style settings
		//	initialize component styles
		if (!this.styleDeclaration)
		{
			this.styleDeclaration = new CSSStyleDeclaration();
		}

		this.styleDeclaration.defaultFactory = function():void
		{
			this.backgroundColor = 16777215;
			this.borderStyle = "solid";
			this.verticalGap = 0;
			this.fontSize = 9;
		};



		//	properties
		this.direction = "vertical";

		//	events
		this.addEventListener("creationComplete", ___Box1_creationComplete);

	}

    /**
     * @private
     **/
	override public function initialize():void
	{
 		mx_internal::setDocumentDescriptor(_documentDescriptor_);

		//	binding mgmt
		_DAGNode_bindingsSetup();

		var target:DAGNode = this;

		if (_watcherSetupUtil == null)
		{
			var watcherSetupUtilClass:Object = getDefinitionByName("_Components_DAGNodeWatcherSetupUtil");
			watcherSetupUtilClass["init"](null);
		}

		_watcherSetupUtil.setup(this,
					function(propertyName:String):* { return target[propertyName]; },
					_bindings,
					_watchers);


		super.initialize();
	}


		import mx.controls.ComboBox;
		import mx.controls.HScrollBar;
		import mx.containers.HBox;
		import mx.rpc.events.ResultEvent;
		import DAG;
		import mx.events.MenuEvent;
		import mx.controls.Alert;
		import mx.skins.halo.BrokenImageBorderSkin;
		import mx.collections.ArrayCollection;
		import mx.rpc.events.FaultEvent;
		import flash.utils.IDataInput;
		import flash.utils.IDataOutput;
		import mx.controls.Alert;
		import mx.core.Repeater;
		import mx.controls.Menu;
			
		private var editedNode:DAGNode;	
		[Bindable]
		public var accSelectedIndx:Number = 0;
		[Bindable]
		public var nodeName:String = "Node";
		[Bindable]
		public var expressionId:int;
		[Bindable]
		public var nodeNumber:String = "1";
		[Bindable]
		public var operatorBetweenAttrAndAssociation:String="AND"; 
		[Bindable]
		public var nodeType:String="";
		[Bindable]
		public var operatorArray:Array = ["AND","OR"];
		[Bindable]
		public var operatorSelectedIndex:int=0;
		[Bindable]
		public var enable:Boolean=true;
		[Bindable]
		public var nodeColor:int =0xFFFFAA;
		
		public var associationList:ArrayCollection;
		public var operatorList:ArrayCollection;
//		public var pathList:ArrayCollection;
		public var dagpathList:ArrayCollection;
		public var outAssociations:ArrayCollection = new ArrayCollection();		
		public var displayoutAssociations:ArrayCollection = new ArrayCollection();		
		public var inAssociations:ArrayCollection = new ArrayCollection();


		[Bindable]
		public function addDisplayOutAssociation(node:String,link:String,operatorIndex:int):void{
			var ass:Association = new Association(node,link,operatorIndex);
			displayoutAssociations.addItem(ass);
		}

		[Bindable]
		public function removeDisplayOutAssociation(node:String,link:String):void{
			var ass:Association;
			for(var i:int=0;i<displayoutAssociations.length;i++)
			{
				ass = Association(displayoutAssociations.getItemAt(i));
				if(ass.associatedLink == link && ass.associatedNode == node)
				{
					displayoutAssociations.removeItemAt(i);
					break;
				}
			}
		}

		[Bindable]
		public function addOutAssociation(node:String,link:String,operatorIndex:int):void{
			var ass:Association = new Association(node,link,operatorIndex);
			outAssociations.addItem(ass);
			}
		[Bindable]
		public function removeOutAssociation(node:String,link:String):void{
			var ass:Association;
			for(var i:int=0;i<outAssociations.length;i++)
			{
				ass = Association(outAssociations.getItemAt(i));
				if(ass.associatedLink == link && ass.associatedNode == node)
				{
					outAssociations.removeItemAt(i);
					break;
				}
			}
		}
		
		[Bindable]
		public function getOutAssociations():ArrayCollection{
			return outAssociations;
		}
		
		[Bindable]
		public function getOutAssociation(node:String):Association{
			var ass:Association;
			for(var i:int=0;i<outAssociations.length;i++)
			{
				ass = Association(outAssociations.getItemAt(i));
				if(ass.associatedNode == node)
				{
					return ass;
				}
			}
			return null;
		}
		
		[Bindable]
		public function addInAssociation(node:String,link:String,operatorIndex:int):void{
			var ass:Association = new Association(node,link,operatorIndex);
			inAssociations.addItem(ass);
		}
		[Bindable]
		public function removeInAssociation(node:String,link:String):void{
			var ass:Association;
			for(var i:int=0;i<inAssociations.length;i++)
			{
				ass = Association(inAssociations.getItemAt(i));
				if(ass.associatedLink == link && ass.associatedNode == node)
				{
					inAssociations.removeItemAt(i);
					break;
				}
			}
		}
		[Bindable]
		public function getInAssociations():ArrayCollection{
			return inAssociations;
		}
		//-------------
		
		public function init():void
		{
			this.parentApplication.rpcService.addEventListener("fault", faultHandler);
		}
		public function readExternal(input:IDataInput):void {
			nodeName = input.readUTF();
			toolTip=input.readUTF();
			expressionId=input.readInt();
			operatorBetweenAttrAndAssociation = input.readUTF();
			nodeType = input.readUTF();
			associationList = input.readObject() as ArrayCollection;
			operatorList = input.readObject() as ArrayCollection;
			//pathList = input.readObject();
			dagpathList = input.readObject() as ArrayCollection;
			
			
		}

		public function writeExternal(out:IDataOutput):void {
			out.writeUTF(nodeName);
			out.writeUTF(toolTip);
			out.writeInt(expressionId);
			out.writeUTF(operatorBetweenAttrAndAssociation);
			out.writeUTF(nodeType);
			out.writeObject(associationList);
			out.writeObject(operatorList);
			//out.writeObject(pathList);
			out.writeObject(dagpathList);
		}
		//---
		
		public function select():void
		{
			this.setStyle("borderColor","blue");
			this.setStyle("borderThickness","2");
		}
		
		public function unSelect():void
		{
			this.setStyle("borderColor","gray");
			this.setStyle("borderThickness","1");
		}
		
		private function selectionEffect():void{
			this.setStyle("color","blue");
		}
		
		// Method to create an Array-based menu.
		private function createAndShow(event:MouseEvent):void {
			var myMenu:Menu=null;
			if(this.parentApplication.view=="Result")
			{
				myMenu= Menu.createMenu(this, outputMenuData, true);
				if(this.nodeType==DAGConstants.CONSTRAINT_ONLY_NODE)
				{
					this.outputMenuData[0].enabled=true;
					this.outputMenuData[1].enabled=false;
				}
				
			}
			else
			{
				myMenu = Menu.createMenu(this, menuData, true);
			}
			myMenu.show(event.stageX,event.stageY);
			myMenu.addEventListener(MenuEvent.ITEM_CLICK,handleMenuEvent);
			
		}
		public function deleteNode():void
		{
				var ass:Association;
				for(var i:int=0;i<inAssociations.length;i++)
				{
					ass = Association(inAssociations.getItemAt(i));
					this.parent.removeChild(this.parent.getChildByName(ass.associatedLink));
					DAGNode(this.parent.getChildByName(ass.associatedNode)).removeOutAssociation(this.name,ass.associatedLink);
					DAGNode(this.parent.getChildByName(ass.associatedNode)).removeDisplayOutAssociation(this.name,ass.associatedLink);
				}
				for(var j:int=0;j<outAssociations.length;j++)
				{
					ass = Association(outAssociations.getItemAt(j));
					this.parent.removeChild(this.parent.getChildByName(ass.associatedLink));
					DAGNode(this.parent.getChildByName(ass.associatedNode)).removeInAssociation(this.name,ass.associatedLink);
				}
				this.parentApplication.rpcService.deleteNode(this.expressionId);
				this.parentApplication.rpcService.addEventListener(ResultEvent.RESULT,deleteHandler);
				
		}
		private function handleMenuEvent(event:MenuEvent):void
		{
			if(event.label == DAGConstants.DELETE)
			{
				deleteNode();
			}
			if(event.label ==DAGConstants.EDIT)
			{
				this.parentApplication.editNode(this);
			}
			if(event.label==DAGConstants.ADD_TO_VIEW)
			{
				this.nodeColor=0xFFFFAA;
				this.outputMenuData[0].enabled=false;
				this.outputMenuData[1].enabled=true;
				this.parentApplication.rpcService.addToView(this.expressionId);
				this.parentApplication.rpcService.addEventListener(ResultEvent.RESULT,addToViewHandler);
			}
			if(event.label==DAGConstants.DELETE_FROM_VIEW)
			{
				if(this.nodeType==DAGConstants.VIEW_ONLY_NODE)
				{
					deleteNode();
				}				
				else
				{
					this.nodeColor=0xffb693;
					this.outputMenuData[0].enabled=true;
					this.outputMenuData[1].enabled=false;
					this.parentApplication.rpcService.deleteFromView(this.expressionId);
					this.parentApplication.rpcService.addEventListener(ResultEvent.RESULT,deleteFromViewHandler);
					
				}
				
			}
		}
		
		public function deleteFromViewHandler(event:ResultEvent):void
		{
			this.parentApplication.rpcService.removeEventListener(ResultEvent.RESULT,deleteFromViewHandler,false);
		}
		public function addToViewHandler(event:ResultEvent):void
		{
			this.parentApplication.rpcService.removeEventListener(ResultEvent.RESULT,addToViewHandler,false);
		}
		public function deleteHandler(event:ResultEvent):void
		{
			this.parent.removeChild(this.parent.getChildByName(this.name));
			this.parentApplication.totalNodeMap.remove(this.name);
			this.parentApplication.rpcService.removeEventListener(ResultEvent.RESULT,deleteHandler,false);
			if(this.nodeType==DAGConstants.CONSTRAINT_VIEW_NODE)
			{
				this.parentApplication.showValidationMessagesToUser(DAGConstants.DELETE_LIMITS_MESSAGE);
			}
		}
		
		public function faultHandler (event:FaultEvent):void {
				// Deal with event.fault.faultString, etc.
			Alert.show(event.fault.faultString, 'Error');
		}
			
	
		// The Array data provider
		[Bindable]
		public var menuData:Array = [	
		{label: "Edit", type: "radio", toggled: false},
		{label: "Delete", type: "radio", toggled: false}
		
		];
		
		[Bindable]
		public var outputMenuData:Array = [	
		{label: "AddToView", type: "radio", toggled: false,enabled:false},
		{label: "DeleteFromView", type: "radio", toggled: false,enable:true}
		
		];
		
		public function setOperatorHandler(event:ResultEvent):void
		{
			this.parentApplication.rpcService.removeEventListener(ResultEvent.RESULT,setOperatorHandler,false);
		}
		
		public function operatorChange(event:Event):void
		{
		 var cb:ComboBox=event.currentTarget as ComboBox;
		 var index:int = cb.name as int;
		 this.parentApplication.rpcService.addEventListener(ResultEvent.RESULT,setOperatorHandler);
		 this.parentApplication.rpcService.setLogicalOperator(this,index,cb.selectedLabel);
		 var outAssociation:Association =  displayoutAssociations.getItemAt(index) as Association;
		 outAssociation.operatorIndex=cb.selectedIndex;
		 var dagPath:IDAGPath = dagpathList.getItemAt(index) as IDAGPath;
		 dagPath.operatorIndex = cb.selectedIndex;	
		 		
		}
	
		
	



    //	supporting function definitions for properties, events, styles, effects
/**
 * @private
 **/
public function ___Box1_creationComplete(event:mx.events.FlexEvent):void
{
	init()
}

/**
 * @private
 **/
public function ___Button1_click(event:flash.events.MouseEvent):void
{
	createAndShow(event);
}

/**
 * @private
 **/
public function __operatorCombo_change(event:mx.events.ListEvent):void
{
	operatorChange(event)
}


	//	binding mgmt
    private var _bindings:Array;
    private var _watchers:Array;
    private function _DAGNode_bindingsSetup():void
    {
        if (!_bindings)
        {
            _bindings = [];
        }

        if (!_watchers)
        {
            _watchers = [];
        }

        var binding:Binding;

        binding = new mx.binding.Binding(this,
            function():uint
            {
                return (nodeColor);
            },
            function(_sourceFunctionReturnValue:uint):void
            {
                _HBox1.setStyle("backgroundColor", _sourceFunctionReturnValue);
            },
            "_HBox1.backgroundColor");
        _bindings[0] = binding;
        binding = new mx.binding.Binding(this,
            function():String
            {
                var result:* = (nodeNumber);
                var stringResult:String = (result == undefined ? null : String(result));
                return stringResult;
            },
            function(_sourceFunctionReturnValue:String):void
            {
				
                _Label1.text = _sourceFunctionReturnValue;
            },
            "_Label1.text");
        _bindings[1] = binding;
        binding = new mx.binding.Binding(this,
            function():String
            {
                var result:* = (nodeName);
                var stringResult:String = (result == undefined ? null : String(result));
                return stringResult;
            },
            function(_sourceFunctionReturnValue:String):void
            {
				
                _Label2.text = _sourceFunctionReturnValue;
            },
            "_Label2.text");
        _bindings[2] = binding;
        binding = new mx.binding.Binding(this,
            function():Object
            {
                return (displayoutAssociations);
            },
            function(_sourceFunctionReturnValue:Object):void
            {
				
                assRep.dataProvider = _sourceFunctionReturnValue;
            },
            "assRep.dataProvider");
        _bindings[3] = binding;
        binding = new mx.binding.RepeatableBinding(this,
            function(instanceIndices:Array,
                     repeaterIndices:Array):String
            {
                var result:* = (assRep.mx_internal::getItemAt(repeaterIndices[0]).associatedNode);
                var stringResult:String = (result == undefined ? null : String(result));
                return stringResult;
            },
            function(_sourceFunctionReturnValue:String,
                     instanceIndices:Array):void
            {
                _Label3[instanceIndices[0]].text = _sourceFunctionReturnValue;
            },
            "_Label3.text");
        _bindings[4] = binding;
        binding = new mx.binding.RepeatableBinding(this,
            function(instanceIndices:Array,
                     repeaterIndices:Array):String
            {
                var result:* = (repeaterIndices[0].toString());
                var stringResult:String = (result == undefined ? null : String(result));
                return stringResult;
            },
            function(_sourceFunctionReturnValue:String,
                     instanceIndices:Array):void
            {
                operatorCombo[instanceIndices[0]].name = _sourceFunctionReturnValue;
            },
            "operatorCombo.name");
        _bindings[5] = binding;
        binding = new mx.binding.RepeatableBinding(this,
            function(instanceIndices:Array,
                     repeaterIndices:Array):Object
            {
                return (operatorArray);
            },
            function(_sourceFunctionReturnValue:Object,
                     instanceIndices:Array):void
            {
                operatorCombo[instanceIndices[0]].dataProvider = _sourceFunctionReturnValue;
            },
            "operatorCombo.dataProvider");
        _bindings[6] = binding;
        binding = new mx.binding.RepeatableBinding(this,
            function(instanceIndices:Array,
                     repeaterIndices:Array):int
            {
                return (assRep.mx_internal::getItemAt(repeaterIndices[0]).operatorIndex);
            },
            function(_sourceFunctionReturnValue:int,
                     instanceIndices:Array):void
            {
                operatorCombo[instanceIndices[0]].selectedIndex = _sourceFunctionReturnValue;
            },
            "operatorCombo.selectedIndex");
        _bindings[7] = binding;
        binding = new mx.binding.RepeatableBinding(this,
            function(instanceIndices:Array,
                     repeaterIndices:Array):Boolean
            {
                return (enable);
            },
            function(_sourceFunctionReturnValue:Boolean,
                     instanceIndices:Array):void
            {
                operatorCombo[instanceIndices[0]].enabled = _sourceFunctionReturnValue;
            },
            "operatorCombo.enabled");
        _bindings[8] = binding;
    }

    private function _DAGNode_bindingExprs():void
    {
        var destination:*;
		[Binding(id='0')]
		destination = (nodeColor);
		[Binding(id='1')]
		destination = (nodeNumber);
		[Binding(id='2')]
		destination = (nodeName);
		[Binding(id='3')]
		destination = (displayoutAssociations);
		[Binding(id='4')]
		destination = (assRep.currentItem.associatedNode);
		[Binding(id='5')]
		destination = (assRep.currentIndex.toString());
		[Binding(id='6')]
		destination = (operatorArray);
		[Binding(id='7')]
		destination = (assRep.currentItem.operatorIndex);
		[Binding(id='8')]
		destination = (enable);
    }

    /**
     * @private
     **/
    public static function set watcherSetupUtil(watcherSetupUtil:IWatcherSetupUtil):void
    {
        (DAGNode)._watcherSetupUtil = watcherSetupUtil;
    }

    private static var _watcherSetupUtil:IWatcherSetupUtil;





    /**
     * @private
     **/
    public var _bindingsByDestination : Object;
    /**
     * @private
     **/
    public var _bindingsBeginWithWord : Object;

}

}