hojo.provide("icallcenter.stateElement.link.dialoutLink");hojo.declare("icallcenter.stateElement.link.dialoutLink",null,{constructor:function(a){this._base=a},_base:null,_callState:"stDialTalking",_changeToolBarState:function(b){hojo.publish("EvtCallToolBarChange",[b._callState])},_switchCallState:function(c){if(c.Event=="ChannelStatus"){if(c.Exten==this._base._phone.sipNo){if(c.ChannelStatus=="Hangup"){this._base._curCallState=this._base._getInvalid();this._changeToolBarState(this._base._curCallState)}else {if(c.ChannelStatus=="Link"){if(c.LinkedChannel.ChannelType=="consultation"){this._base._curCallState=this._base._getConsultationLink();this._changeToolBarState(this._base._curCallState)}else {if(c.LinkedChannel.ChannelType=="ThreeWayCall"){this._base._curCallState=this._base._getThreeWayCallLink();this._changeToolBarState(this._base._curCallState)}}}else {if(c.ChannelStatus=="hold"){this._base._curCallState= new icallcenter.stateElement.hold();this._changeToolBarState(this._base._curCallState)}}}}}},_publish:function(){}})