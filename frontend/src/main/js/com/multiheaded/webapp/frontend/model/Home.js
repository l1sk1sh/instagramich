import React, { Component } from 'react';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: false
        }
    }

    render() {
        return (
            <div>Hi from Home page</div>
        );
    };
}

export default Home;