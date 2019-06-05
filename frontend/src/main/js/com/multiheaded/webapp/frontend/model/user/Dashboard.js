import React, { Component } from 'react';

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: false
        }
    }

    render() {
        return (
            <div>Hi from Dashboard page</div>

        );
    };
}

export default Dashboard;