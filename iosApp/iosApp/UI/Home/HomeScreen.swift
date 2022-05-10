//
//  HomeScreen.swift
//  iosApp
//
//  Created by Suraj GP on 28/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct HomeScreen: View {
    
    @State var selected: Int = 0
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            NavigationView {
                TabView(selection: $selected) {
                    SearchScreen()
                        .tabItem {
                            Label("Search", systemImage: "magnifyingglass")
                        }
                        .navigationBarHidden(true)
                    
                    FavoritesScreen()
                        .tabItem {
                            Label("Favorites", systemImage: "heart")
                        }
                        .navigationBarHidden(true)
                    
                    ReferenceScreen()
                        .tabItem {
                            Label("References", systemImage: "note.text")
                        }
                        .navigationBarHidden(true)
                }
            }
        }
    }
}
