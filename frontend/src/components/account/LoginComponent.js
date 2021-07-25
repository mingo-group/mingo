import React from 'react'

// Check with backend for how/ what to submit

const LoginComponent = () => {
    return (
        <div className="login">
            <form >
                <h3 >Please Provide Login Information</h3>

                <input type="text" placeholder="Username"/>

                <input type="password" placeholder="Password"/>
            </form>
        </div>
    )
}

export default LoginComponent