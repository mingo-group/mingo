import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom'

import Header from './components/utilities/HeaderComponent'
import Landing from './components/LandingComponent'
import Terms from './components/utilities/TermsComponent'
import Preferences from './components/account/PreferencesComponent'
import Login from './components/account/LoginComponent'
import Register from './components/account/RegisterComponent'
import Profile from './components/account/ProfileComponent'

import './App.css';

// Basic routing - No High Level State

function App() {
  return (
    <div className="App">
      <Router >
        <Header />
        <Switch>
          <Route exact path="/" component={Landing}/>
          <Route path="/login" component={Login}/>
          <Route path="/register" component={Register}/>
          <Route path="/preferences" component={Preferences}/>
          <Route path="/terms" component={Terms}/>
          <Route path="/profile" component={Profile}/>
          <Redirect to="/"/>
        </Switch>
      </Router>
    </div>
  );
}

export default App;

