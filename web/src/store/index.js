import { createStore } from 'vuex'

export default createStore({
    state: {
        member: {},
    },
    mutations: {
        setMember (state, _member) {
            state.member = _member;
        }
    },
})
