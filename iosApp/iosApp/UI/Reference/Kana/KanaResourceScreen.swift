//
//  KanaResourceScreen.swift
//  iosApp
//
//  Created by Suraj GP on 10/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct KanaResourceScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        ZStack {
            
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                
                AppTopBar(navigateUpIcon: "arrow.backward", title: "Kana") {
                    self.presentationMode.wrappedValue.dismiss()
                }
                .padding()
            }
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}
