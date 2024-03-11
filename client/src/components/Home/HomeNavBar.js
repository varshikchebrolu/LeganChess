import React from 'react'

function HomeNavBar(){
    return(
        <div className="navbar header navbar-expand-lg navbar-dark bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand" href='#'>The Masked Code Avengers</a>
                <div>
                    <ul className="navbar-nav">
                        <li className="nav-item navbar-light">
                            <a className="nav-link">Profile</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link">My account</a>
                        </li>
                        <li className="nav-item navbar-light" >
                            <a className="nav-link" onClick={refreshPage}>Logout</a>
                        </li>
                    </ul>

                </div>

            </div>
        </div>
    )
}
function refreshPage() {
    window.location.reload(false);
}

export default HomeNavBar
