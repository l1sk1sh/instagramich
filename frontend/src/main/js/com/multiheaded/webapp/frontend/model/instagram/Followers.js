import React, { Component } from 'react';
import { createSInstagramUser } from '../../util/APIUtils';
import { Form, Input, Button, Icon, Select, Col, notification } from 'antd';
const Option = Select.Option;
const FormItem = Form.Item;
const { TextArea } = Input;

class Followers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: {
                text: ''
            },
            password: {
                text: ''
            }
        };
        // Binding of functions
    }

    // Functions
    render() {
        return (
            <div>Hi from Followers
            
            </div>
        );
    };
}

export default Followers;