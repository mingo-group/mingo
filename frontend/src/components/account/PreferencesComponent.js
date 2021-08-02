import React, {Component} from 'react'
import SingleOption from './SingleOptionComponent'
import SingleFinalOption from './SingleFinalOptionComponent'

// Boolean state used for each set of preferences
// selectedThings gets fleshed out as options are selected
// options are for data that will be selected and sent
//  Check with backend for what to add and how to submit

class PreferencesComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            foodsTouched: false,
            foodsActive: true,
            sportsActive: false,
            entertainmentActive: false,
            openDrop: false,

            selectedThings: [],
            selectedSports: [],

            // theObject is for the futher types of sports teams
            theObject: {
                Basketball: ['San Antonio, Spurs', 'Los Angeles'],
                Soccer: ['USWNT', 'Manchester united'],
                Football: ['St. Louis Rams', 'Chargers'],
                Squash: ['Neighboorhood team'],
                Tennis: ['Andy Murray', 'Nader'],
                Golf: ['Tiger Woods', 'Jack Nicolas']

            },
            sportsPick: [],
            final: false,
            finalSpecifics: [],

            foodOptions: [ 'Italian','indian', 'Chinese', 'Japanese', 'Mexican', 'Meditarranean'],
            sportsOptions: ['Football', 'Soccer', 'Basketball', 'Squash', 'Tennis', 'golf'],
            entertainmentOptions: ['Music', 'Dancing', 'Movies', 'Theater', "Art", 'Board Games'],
            recreationOptions: ['Parks', '']
        }

        
        this.getAllSelections = this.getAllSelections.bind(this)
        this.getAllFood = this.getAllFood.bind(this)
        this.getAllSports = this.getAllSports.bind(this)
        this.addThing = this.addThing.bind(this)
        this.removeThing = this.removeThing.bind(this)
        this.toggleOpen = this.toggleOpen.bind(this)
        this.addFinalThing = this.addFinalThing.bind(this)
        this.removeFinalThing = this.removeFinalThing.bind(this)
    }


    // Activates the sports selection
    getAllFood = (e) => {
        e.preventDefault()
        
        this.setState({
            foodsActive: false,
            sportsActive: true
        })
    }

    // Activates the entertainment section
    getAllSports = (e) => {
        e.preventDefault()
        
        this.setState({
            
            sportsActive: false,
            entertainmentActive: true
           
        })
    }

    // This triggers the more selective sports options
    getAllSelections = (e) => {
        e.preventDefault()
        

        const selectedCategories = this.state.selectedSports.filter(item => this.state.theObject[item])
           
        let selectedArray = selectedCategories.map(item => {
                return {
                    [item]: this.state.theObject[item]
                }
            })
        
        this.setState({
            foodsTouched: true,
            sportsPick: selectedArray,
            final: true,
            entertainmentActive: false
            
        })
        console.log(Object.keys(selectedArray[0]))
    }

    // Handles selection for food and entertainment
    addThing = (thing) => {
        this.setState(state => {
            const selectedThings = [...state.selectedThings, thing]

            return {
                selectedThings
            }
        })
    }

    // Handles selection for sports 
    addSportThing = (thing) => {
        this.setState(state => {
            const selectedSports = [...state.selectedSports, thing]

            return {
                selectedSports
            }
        })
    }

    // Handles selection for the last level of sports
    addFinalThing = (thing) => {
        this.setState(state => {
            const finalSpecifics = [...state.finalSpecifics, thing]

            return {
                finalSpecifics
            }
        })
    }

    removeThing = (thing) => {
        this.setState(state => {
            const selectedThings = state.selectedThings.filter((item) => thing !== item)

            return {
                selectedThings
            }
        })
    }

    removeSportThing = (thing) => {
        this.setState(state => {
            const selectedSports = state.selectedSports.filter((item) => thing !== item)

            return {
                selectedSports
            }
        })
    }

    removeFinalThing = (thing) => {
        this.setState(state => {
            const finalSpecifics = state.finalSpecifics.filter((item) => thing !== item)

            return {
                finalSpecifics
            }
        })
    }

    toggleOpen= () => {
        this.setState(prevState => {
            return { openDrop: !prevState.openDrop }
        })

    }

    render(){
        const foodOptions = this.state.foodOptions.map((option, index) => {
            return <SingleOption 
                option={option} 
                key={index} 
                addThing={this.addThing}
                removeThing={this.removeThing}/>
        })

        const sportsOptions = this.state.sportsOptions.map((option, index) => {
            return <SingleOption 
                option={option} 
                key={index} 
                addThing={this.addSportThing}
                removeThing={this.removeSportThing}/>
        })

        const entertainmentOptions = this.state.entertainmentOptions.map((option, index) => {
            return <SingleOption 
                option={option} 
                key={index} 
                addThing={this.addThing}
                removeThing={this.removeThing}/>
        })
        
        const finalOptions = this.state.sportsPick.map((selection, index) => {
            const cat = Object.keys(selection)
            return <form key={index} className="final-form">
                        <h3>{cat}</h3>
                        <section className="options row">
                            {selection[cat].map((option, index) => {
                                return <SingleFinalOption 
                                    option={option} 
                                    key={index} 
                                    addFinalThing={this.addFinalThing}
                                    removeFinalThing={this.removeFinalThing}
                                />
                            })}
                        </section>
                        {/* <input type="submit" className="sub-btn" value="Submit Answers"/> */}
                    </form>
        })
        
        
    return (
        <div className="login">
            <div className="container preferences-body">
                {this.state.foodsActive && <form onSubmit={this.getAllFood}>
                    <h3 >What are your Food Preferences?</h3>

                    <section className="options row">
                        {foodOptions}
                    </section>
                
                    <input type="submit" className="sub-btn" value="Submit Answers"/>
                
                </form>}

                {this.state.sportsActive && <form onSubmit={this.getAllSports}>
                    <h3 >What are your Sports preferences?</h3>
            
                    <section className="options row">
                        {sportsOptions}
                    </section>
                
                    <input type="submit" className="sub-btn" value="Submit Answers"/>
                
                </form>}

                {this.state.entertainmentActive && <form onSubmit={this.getAllSelections}>
                    <h3 >What are your Entertainment Preferences?</h3>
                
                    <section className="options row">
                        {entertainmentOptions}
                    </section>
                
                    <input type="submit" className="sub-btn" value="Submit Answers"/>
                
                </form>}
                {this.state.final && finalOptions}
            </div>
        </div>
    )
            }
}

export default PreferencesComponent;