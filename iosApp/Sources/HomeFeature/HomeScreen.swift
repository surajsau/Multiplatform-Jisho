//
//  HomeScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import SearchFeature
import TaggedFeature

struct HomeScreen: View {
    
    @State var selected: Int = 0
    
    var body: some View {
        ZStack {
            NavigationView {
                TabView(selection: $selected) {
                    SearchScreen()
                        .tabItem {
                            Label("Search", systemImage: "magnifyingglass")
                        }
                        .navigationBarHidden(true)
                    
                    TaggedScreen()
                        .tabItem {
                            Label("Favorites", systemImage: "heart")
                        }
                        .navigationBarHidden(true)
                    
//                    ReferenceScreen()
//                        .tabItem {
//                            Label("References", systemImage: "note.text")
//                        }
//                        .navigationBarHidden(true)
                }
            }
            .navigationViewStyle(.stack)
        }
    }
}
