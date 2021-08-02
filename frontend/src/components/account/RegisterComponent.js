import React, { Component } from 'react'

// Basic form to flesh out
class RegisterComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name: '',
            password: '',
            email: '',
            location: ''
        }

        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleSubmit(e){
        e.preventDefault()
        console.log(this.state)
    }

    render(){
        const handleInput = (e) => {
            e.preventDefault()
            const target = e.target.name
    
            this.setState({
                [target]: e.target.value
            })
        }
    return (
        <div className="register">
            <form onSubmit={this.handleSubmit}>
                <h3 >Tell me about yourself</h3>

                <input 
                    type="text" 
                    placeholder="Name"
                    name="name"
                    value={this.state.name}
                    onChange={(e) => handleInput(e)}/>

                <input 
                    type="email" 
                    placeholder="Email"
                    name="email"
                    value={this.state.email}
                    onChange={(e) => handleInput(e)}/>

                <input 
                    type="password" 
                    placeholder="Password"
                    name="password"
                    value={this.state.password}
                    onChange={(e) => handleInput(e)}/>

                <input 
                    type="text" 
                    placeholder="Enter Location"
                    name="location"
                    value={this.state.location}
                    onChange={(e) => handleInput(e)}/>

                <input type="submit" value="Submit"/>

            </form>
        </div>
    )
            }
}

export default RegisterComponent;