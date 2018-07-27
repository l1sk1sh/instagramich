import React, { Component } from 'react';

class SIUser extends Component {
    render() {
        return (
            <div className="suser-content">
                <div className="suser-header">
                    <div></div>
                    {this.props.suser.iUser.username}
                </div>

            </div>
        );
    };
}

export default SIUser;