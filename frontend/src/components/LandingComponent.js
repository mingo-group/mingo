import React from 'react'
import { Link } from 'react-router-dom'

// Modeled after what I was provided with in video
// Assets provided for MINGO will help

const LandingComponent = () => {
    return (
        <div className="land">
            <div id="logo">
                M
            </div>
            <h1 className="text">MINGO</h1>
            <h4 className="text">New to the area?</h4>
            <button>GET STARTED</button>
            <span>
                <Link className="accent" to="/login">Already a Member?</Link>
                </span>
            <span className="accent">
                <Link to="/terms">Terms of Sevice</Link>
            </span>
        </div>
    )
}

export default LandingComponent;