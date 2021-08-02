import React, { Component } from 'react'

// Local state is for selected in color only
// props method adds or removes from selection in parent component

class SingleFinalOptionComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            selected: false
        }

        this.selectThis = this.selectThis.bind(this)
        this.deselectThis = this.deselectThis.bind(this)
    }

    selectThis(option){
        this.setState(prevState => {
            return { selected: !prevState.selected }
        })

        this.props.addFinalThing(option)
    }

    deselectThis(option){
        this.setState(prevState => {
            return { selected: !prevState.selected }
        })

        this.props.removeFinalThing(option)
    }

   

    render() {
        if(this.state.selected){
            return (
                <div className="blue col col-md-5" onClick={() => this.deselectThis(this.props.option)}>
                    {this.props.option}
                </div>
            )
        } else {
            return (
                <div className="pref-click col col-md-5" onClick={() => this.selectThis(this.props.option)}>
                    {this.props.option}
                </div>
            )
        }
        
    }
}

export default SingleFinalOptionComponent;