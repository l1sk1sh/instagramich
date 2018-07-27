import React, {Component} from 'react';
import {
    Link,
    withRouter
} from 'react-router-dom';
import '../assets/css/AppHeader.css';
import {Layout, Menu, Dropdown, Icon} from 'antd';

const Header = Layout.Header;

class AppHeader extends Component {
    constructor(props) {
        super(props);
        this.handleMenuClick = this.handleMenuClick.bind(this);
    }

    handleMenuClick({key}) {
        if (key === "logout") {
            this.props.onLogout();
        }
    }

    render() {
        let menuItems;
        if (this.props.currentUser) {
            menuItems = [
                <Menu.Item key="/dashboard">
                    <Link to="/dashboard">
                        <Icon type="home" className="nav-icon"/>
                    </Link>
                </Menu.Item>,
                <Menu.Item key="/sinstagram-users" className="sinstagram-users-menu">
                    <InstagramUsersDropdownMenu
                        currentUser={this.props.currentUser}
                        handleMenuClick={this.handleMenuClick}/>
                </Menu.Item>,
                <Menu.Item key="/profile" className="profile-menu">
                    <ProfileDropdownMenu
                        currentUser={this.props.currentUser}
                        handleMenuClick={this.handleMenuClick}/>
                </Menu.Item>
            ];
        } else {
            menuItems = [
                <Menu.Item key="/login">
                    <Link to="/login">Login</Link>
                </Menu.Item>,
                <Menu.Item key="/signup">
                    <Link to="/signup">Signup</Link>
                </Menu.Item>
            ];
        }

        return (
            <Header className="app-header">
                <div className="container">
                    <div className="app-title">
                        <Link to="/">Webapp</Link>
                    </div>
                    <Menu
                        className="app-menu"
                        mode="horizontal"
                        selectedKeys={[this.props.location.pathname]}
                        style={{lineHeight: '64px'}}>
                        {menuItems}
                    </Menu>
                </div>
            </Header>
        );
    }
}

function ProfileDropdownMenu(props) {
    const dropdownMenu = (
        <Menu onClick={props.handleMenuClick} className="profile-dropdown-menu">
            <Menu.Item key="user-info" className="dropdown-item" disabled>
                <div className="user-full-name-info">
                    {props.currentUser.name}
                </div>
                <div className="username-info">
                    @{props.currentUser.username}
                </div>
            </Menu.Item>
            <Menu.Divider />
            <Menu.Item key="profile" className="dropdown-item">
                <Link to={`/profile`}>Profile</Link>
            </Menu.Item>
            <Menu.Item key="logout" className="dropdown-item">
                Logout
            </Menu.Item>
        </Menu>
    );

    return (
        <Dropdown
            overlay={dropdownMenu}
            trigger={['click']}
            getPopupContainer={() => document.getElementsByClassName('profile-menu')[0]}>
            <a className="ant-dropdown-link">
                <Icon type="user" className="nav-icon" style={{marginRight: 0}}/> <Icon type="down"/>
            </a>
        </Dropdown>
    );
}

function InstagramUsersDropdownMenu(props) {
    const dropdownMenu = (
        <Menu onClick={props.handleMenuClick} className="sinstagram-users-dropdown-menu">
            <Menu.Item key="sinstagram-users" className="dropdown-item">
                <Link to={`/susers`}>Your Instagram Accounts</Link>
            </Menu.Item>
            <Menu.Item key="sinstagram-users-new" className="dropdown-item">
                <Link to={`/susers/new`}>Add New Instagram Account</Link>
            </Menu.Item>
            <Menu.Item key="sinstagram-user" className="dropdown-item">
                <Link to={`/susers/:username`}>Test Instagram Account</Link>
            </Menu.Item>
            <Menu.Item key="sinstagram-user-followers" className="dropdown-item">
                <Link to={`/susers/:username/followers`}>Test Instagram Account Followers</Link>
            </Menu.Item>
        </Menu>
    );

    return (
      <Dropdown
          overlay={dropdownMenu}
          trigger={['click']}
          getPopupContainer={() => document.getElementsByClassName('sinstagram-users-menu')[0]}>
          <a className="ant-dropdown-link">
              <Icon type="instagram" className="nav-icon" style={{marginRight: 0}}/> <Icon type="down"/>
          </a>
      </Dropdown>
    );
}

export default withRouter(AppHeader);