import React, { Component } from 'react';

class SInstagramUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: false
        }
    }

    render() {
        return (
            <div>Hi from SInstagramUser</div>
        );
    };
}

export default SInstagramUser;