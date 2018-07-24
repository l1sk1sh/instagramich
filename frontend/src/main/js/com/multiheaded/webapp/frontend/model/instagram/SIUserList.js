import React, { Component } from 'react';
import { Link, Route, Switch } from 'react-router-dom';
import NewSIUser from "./NewSIUser";
import PrivateRoute from "../../common/PrivateRoute";

class SIUserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: false
        }
    }

    render() {
        return (
            <div>Hi from SIUserList
                <Switch>
                    <PrivateRoute authenticated={this.state.isAuthenticated} path="/susers/new" component={NewSIUser}
                                  currentUser={this.state.currentUser}/>
                </Switch>
            </div>
        );
    };
}

export default SIUserList;