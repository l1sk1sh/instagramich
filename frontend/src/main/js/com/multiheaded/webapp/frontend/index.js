// TODO Move to Material ui
// TODO Audit npm for vulnerabilities
// TODO Fix arrow functions in SignUp (babel config)
// TODO Separate messages for translation opportunities
// TODO Warning during webpack launch
import React from 'react';
import ReactDOM from 'react-dom';
import './assets/css/index.css';
import App from './app/App';
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter as Router } from 'react-router-dom';

ReactDOM.render(
    <Router>
        <App />
    </Router>,
    document.getElementById('root')
);

registerServiceWorker();
