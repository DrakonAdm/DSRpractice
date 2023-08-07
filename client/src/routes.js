import Profile from "./pages/person/Profile";
import {
    ANOTHER_PROFILE_ROUTE, AUTH_ROUTE,
    CHANGE_PROFILE_ROUTE,
    LIST_FRIEND_ROUTE,
    LIST_INVITE_ROUTE,
    LIST_MESSAGE_ROUTE,
    PROFILE_ROUTE, REGISTRATION_ROUTE,
    SEARCH_ROUTE
} from "./utils/consts";
import ChangeProfile from "./pages/person/ChangeProfile";
import Search from "./pages/person/Search";
import ListFriend from "./pages/person/ListFriend";
import ListInvite from "./pages/person/ListInvite";
import ListMessage from "./pages/message/ListMessage";
import AnotherProfile from "./pages/person/AnotherProfile";
import Auth from "./pages/auth/Auth";
import Registration from "./pages/auth/Registration";

export const authRoutes = [
    {
        path: PROFILE_ROUTE,
        Component: Profile
    },
    {
        path: CHANGE_PROFILE_ROUTE,
        Component: ChangeProfile
    },
    {
        path: SEARCH_ROUTE,
        Component: Search
    },
    {
        path: LIST_FRIEND_ROUTE,
        Component: ListFriend
    },
    {
        path: LIST_INVITE_ROUTE,
        Component: ListInvite
    },
    {
        path: LIST_MESSAGE_ROUTE,
        Component: ListMessage
    },
    {
        path: ANOTHER_PROFILE_ROUTE + '/:id',
        Component: AnotherProfile
    },
]

export const publicRoutes = [
    {
        path: AUTH_ROUTE,
        Component: Auth
    },
    {
        path: REGISTRATION_ROUTE,
        Component: Registration
    },
]