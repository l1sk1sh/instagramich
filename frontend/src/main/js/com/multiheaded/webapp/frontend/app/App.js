'use strict';
// TODO implement translations (react-localize-redux)
import React, { Component } from 'react';
import '../assets/css/App.css';
import { Route, withRouter, Switch } from 'react-router-dom';

import { getCurrentUser } from '../util/APIUtils';
import { ACCESS_TOKEN } from '../constants';

import Home from '../model/Home'
import Login from '../model/user/Login';
import Signup from '../model/user/Signup';
import Dashboard from '../model/user/Dashboard';
import Profile from '../model/user/Profile';
import AppHeader from '../common/AppHeader';
import NotFound from '../common/NotFound';
import LoadingIndicator from '../common/LoadingIndicator';
import PrivateRoute from '../common/PrivateRoute';
import SIUserList from '../model/instagram/SIUserList'
import SIUser from '../model/instagram/SIUser'

import { Layout, notification } from 'antd';
import NewSIUser from "../model/instagram/NewSIUser";
import Followers from "../model/instagram/Followers";
const { Content } = Layout;

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false
        };
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

        notification.config({
            placement: 'bottomRight',
            top: 70,
            duration: 3,
        });
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });
    }

    componentWillMount() {
        this.loadCurrentUser();
    }

    handleLogout(redirectTo="/", notificationType="success", description="You're successfully logged out.") {
        localStorage.removeItem(ACCESS_TOKEN);

        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);

        notification[notificationType]({
            message: 'Webapp',
            description: description,
        });
    }

    handleLogin() {
        notification.success({
            message: 'Webapp',
            description: "You're successfully logged in.",
        });
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    render() {
        if(this.state.isLoading) {
            return <LoadingIndicator />
        }
        return (
            <Layout className="app-container">
                <AppHeader isAuthenticated={this.state.isAuthenticated}
                           currentUser={this.state.currentUser}
                           onLogout={this.handleLogout} />

                <Content className="app-content">
                    <div className="container">
                        <Switch>
                            <Route exact path="/" component={Home} />
                            <Route path="/login" render={(props) => <Login onLogin={this.handleLogin} {...props} />} />
                            <Route path="/signup" component={Signup}> </Route>
                            <PrivateRoute authenticated={this.state.isAuthenticated} path="/profile"
                                          component={Profile} currentUser={this.state.currentUser} />
                            <PrivateRoute authenticated={this.state.isAuthenticated} path="/dashboard"
                                          component={Dashboard} currentUser={this.state.currentUser} />
                            <PrivateRoute authenticated={this.state.isAuthenticated} exact path="/susers/new"
                                          component={NewSIUser} currentUser={this.state.currentUser} />
                            <PrivateRoute authenticated={this.state.isAuthenticated} path="/susers/:susername/followers"
                                          component={Followers} currentUser={this.state.currentUser} />
                            <PrivateRoute authenticated={this.state.isAuthenticated} path="/susers/:susername"
                                          component={SIUser} currentUser={this.state.currentUser} />
                            <PrivateRoute authenticated={this.state.isAuthenticated} exact path="/susers"
                                          component={SIUserList} currentUser={this.state.currentUser} />
                            <Route component={NotFound} />
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}

export default withRouter(App);
