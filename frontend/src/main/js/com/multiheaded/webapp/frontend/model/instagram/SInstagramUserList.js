import React, { Component } from 'react';

class SInstagramUserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: false
        }
    }

    render() {
        return (
            <div>Hi from SInstagramUserList</div>
        );
    };
}

export default SInstagramUserList;