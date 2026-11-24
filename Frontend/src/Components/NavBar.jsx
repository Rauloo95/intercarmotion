import { Link } from "react-router-dom"
import { useAuth } from '../Context/AuthContext.jsx'
import { useNavigate } from 'react-router-dom';






function NavBar() {
    const { isAuthenticated, logout, user } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <nav className="flex items-center justify-between px-6 py-4 bg-slate-900 text-white">
            <Link to="/" className="text-xl font-bold">
                InterCarMotion
            </Link>

            <div className="flex items-center gap-4">
                <Link to="/">Home</Link>
                {isAuthenticated ? (
                    <>
                        <Link to="/favourites">Favoritos</Link>
                        {user?.username && <span className="text-sm text-slate-300">{user.username}</span>}
                        <button
                            onClick={handleLogout}
                            className="px-3 py-1 bg-red-600 rounded hover:bg-red-700"
                        >
                            Cerrar sesión
                        </button>
                    </>
                ) : (
                    <>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Registro</Link>
                    </>
                )}
            </div>
        </nav>
    )


}
export default NavBar

