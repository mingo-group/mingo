import React, { Component } from 'react'

// Making similar model to how preferences are handled 
export default class ProfileComponent extends Component {
    constructor (props){
        super(props)
        this.state = {

        }

    }

    submitAnswers = () => {

    }

    render() {
        return (
            <div className="profile">
                <div className="intro-card">
                    <div>
                        <h2>Someone's Profile</h2>
                        <img src="https://www.pngitem.com/pimgs/m/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png"/>
                    </div>
                    <div>
                        <h3>This is where we could put some kind of geolocation info.</h3>
                    </div>
                </div>
                <div>
                    <h2>Then we can have particulars about the individual</h2>
                    <h3>Your favorites:</h3>
                    <h4>Sports</h4>
                    <ul>
                        <li>Basketball</li>
                        <li>Soccer</li>
                    </ul>
                </div>
                <form>
                    <input type="number" value={40}/>
                    <select name="unit">
                        <option value="miles">Miles</option>
                        <option value="feet">Feet</option>
                    </select>
                </form>
            </div>
        )
    }
}



// import React from 'react'

// export default function ProfileComponent() {
//     return (
//         <div>
//             <h1>Profile Page</h1>
            
//         </div>
//     )
// }
