import React from 'react'
import { Link } from 'react-router-dom'

const HeaderComponent = () => {
    return (
        <div className="header">
            <div id="light-up" className="mr-3"></div>
            <span>
                <Link to="/" className="mr-3">Home</Link>
            </span>
            <span>
                <Link to="/login">Login</Link>
            </span>
            <span>
                <Link to="/register">Register</Link>
            </span>
            <span>
                <Link to="/preferences">Preferences</Link>
            </span>
            <span>
                <Link to="/profile">Profile</Link>
            </span>
        </div>
    )
}

export default HeaderComponent;